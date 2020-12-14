<template>
  <div class="home">
    <Header></Header>
    <span class="count" ><font color="#dc143c">共{{this.total}}篇</font></span>
    <el-row id="artList" type="flex" justify="space-around">
      <el-col :span="20">
        <el-row class="art-item" v-for="(blog,index) in blogs" :key="index">
          <el-card shadow="hover">
            <h5><router-link class="art-title" tag="span":to="{name: 'BlogDetail', params: {blogId: blog.id}}">{{ blog.title }}</router-link></h5>
            <el-row class="art-info d-flex align-items-center justify-content-start">
              <div class="art-time"><i class="el-icon-time"></i>{{blog.created}}</div>
              <div class="d-flex align-items-center"><img class="tag" src="../assets/tag.png" />：
                <el-tag size="mini">{{blog.tag}}</el-tag>
              </div>
            </el-row>
            <el-row class="art-body">
              <div class="side-img hidden-sm-and-down"><img class="art-banner" src="../assets/vue.jpg"></div>
              <div class="side-abstract">
                <div class="art-abstract">
                  {{blog.description}}
                </div>
                <div class="art-more">
                  <router-link :to="{name: 'BlogDetail', params: {blogId: blog.id}}">
                    <el-button plain>{{$t('home.readMore')}}</el-button>
                  </router-link>
                  <div class="view"><i class="el-icon-view"></i>{{ blog.views }}</div>
                </div>
              </div>
            </el-row>
          </el-card>
          <!--          <img class="star" src="../assets/star.png" /> 右上hot标签-->
        </el-row>
        <div class="block pagination">
          <el-pagination class="mpage"
                         background
                         layout="prev, pager, next"
                         :current-page="currentPage"
                         :page-size="pageSize"
                         :total="total"
                         @current-change=page>
          </el-pagination>
        </div>
      </el-col>
    </el-row>

  </div>
</template>
<script>
import Header from "../components/Header";

export default {
  name: "Archive",
  components: {
    Header
  },
  data() {
    return {
      blogs: {},
      currentPage: 1,
      total: 0,
      pageSize: 5
    }
  },
  methods: {
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
#side .item {
  margin-bottom: 30px;
}

.art-item {
  margin-bottom: 30px;
  position: relative;
}

.art-item .star {
  width: 60px;
  height: 60px;
  position: absolute;
  top: 0;
  right: 0;
}

img.tag {
  width: 16px;
  height: 16px;
}

.art-title {
  border-left: 3px solid #F56C6C;
  padding-left: 5px;
  cursor: pointer;
}

.art-title:hover {
  padding-left: 10px;
  color: #409EFF;
}

.art-time {
  margin-right: 20px;
}

.art-body {
  display: flex;
  padding: 10px 0;
}

.side-img {
  height: 150px;
  width: 270px;
  overflow: hidden;
  margin-right: 10px;
}

img.art-banner {
  width: 100%;
  height: 100%;
  transition: all 0.6s;
}

img.art-banner:hover {
  transform: scale(1.4);
}

.side-abstract {
  flex: 1;
  display: flex;
  flex-direction: column;
}

.art-abstract {
  flex: 1;
  color: #aaa;
}

.art-more {
  height: 40px;
  display: flex;
  justify-content: space-between;
  align-items: flex-end;
}

.art-more .view {
  color: #aaa;
}
h5{
  font-size: 18px;
}
.pagination {
  background-color: #F9F9F9;
}
.count{
  font-weight: bold;
  font-size: 150%;
  position: relative;
  left: 220px;
}
</style>