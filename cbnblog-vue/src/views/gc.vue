<template>
  <div class="block">
    <Header></Header>
    <el-container>
      <el-aside width="200px" style="height: 500px">
        <Asider></Asider>
      </el-aside>
      <el-container>
          <el-timeline direction="vertical">
            <el-timeline-item placement="top" v-for="(blog,index) in blogs" :key="index">
              <el-card shadow="hover" class="mycard">
                <el-row class="art-info">
                  <router-link class="title1" :to="{name: 'BlogDetail', params: {blogId: blog.id}}">
                    {{ blog.title }}
                  </router-link>
                  <el-button class="delete" type="text" @click="recovery(blog.id)">恢复文章</el-button>
                  <span>于{{ blog.updated }}删除</span>
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

import Asider from "@/components/asider";

export default {
  name: "gc",
  components: {Asider},
  data() {
    return {
      blogs: [],
      currentPage: 1,
      total: 0,
      pageSize: 5
    }
  },
  methods: {
    recovery(id) {
      this.$axios.put('/blog/recovery/'+id).then(
          this.$router.go(0)
      )
    },
    page(currentPage) {
      const _this = this
      _this.$axios.get("/admin/gcblogs?currentPage=" + currentPage).then(res => {
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
  position: absolute;
  right: 10px;
}

.title1 {
  font-size: 15px;
}
</style>