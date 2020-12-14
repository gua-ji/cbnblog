<template>
  <div class="block">
    <Header></Header>
    <el-container>
      <el-aside width="200px" style="height: 500px">
        <Asider></Asider>
      </el-aside>
      <el-container direction="vertical">
          <el-timeline>
            <el-timeline-item :timestamp="blog.created" placement="top" v-for="(blog,index) in blogs" :key="index">
            <el-card shadow="hover" class="mycard">
              <el-row class="art-info">
                <router-link class="title1" :to="{name: 'BlogDetail', params: {blogId: blog.id}}">
                  {{ blog.title }}
                </router-link>
                  <el-button class="delete" type="text" @click="del(blog.id)">删除</el-button>
              </el-row>
              <p>{{ blog.description }}</p>
            </el-card>
            </el-timeline-item>
          </el-timeline>
          <el-pagination class="mpage"
                         background
                         layout="prev, pager, next"
                         :current-page="currentPage"
                         :page-size="pageSize"
                         :total="total"
                         @current-change=page
                         direction="vertical">

          </el-pagination>
      </el-container>
    </el-container>
  </div>
</template>
<script>
import Header from "../components/Header";
import Asider from "@/components/asider";


export default {
  name: "Admin",
  components: {
    Asider,

    Header
  },
  data() {
    return {
      visible: false,
      blogs: {},
      currentPage: 1,
      total: 0,
      pageSize: 5
    }
  },
  methods: {
    del(did) {
      this.$axios.delete("/blog/" + did).then(
          this.$router.go(0)
      )
    },
    page(currentPage) {
      const _this = this
      _this.$axios.get("/blogs?currentPage=" + currentPage).then(res => {
        console.log(res)
        _this.blogs = res.data.data.records
        _this.currentPage = res.data.data.current
        _this.total = res.data.data.total
        _this.pageSize = res.data.data.size

      })
    }
  },
  created() {
    this.page(1)
  }
}
</script>

<style scoped>

.mpage {
  margin: 0 auto;
  text-align: center;
}

.block {
  position: relative;
  right: 35px;

}

.mycard {
  position: relative;
  top: 0px;
}

.art-info {
  font-size: 13px;
  color: #B3C0D1;
}

.delete {
  font-size: 12px;
}

.title1 {
  font-size: 15px;
}
</style>