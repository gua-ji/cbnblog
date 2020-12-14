<template>
  <div>
    <Header></Header>
    <el-row class="main" type="flex" justify="center">
      <el-col :span="16">
        <div id="artcle-info">
          <h2 class="text-center"><strong>{{ blog.title }}</strong></h2>
          <!-- 描述：文章信息 -->
          <div class="text-center timeAndView">
						<span class="article-time">
							<i class="el-icon-time"></i>
							发表于：<span>{{blog.created}}</span>
						</span>
            &nbsp;|&nbsp;
            <span class="article-views">
							<i class="el-icon-view"></i>
							阅读量：<span>1</span>万
						</span>
            <el-link icon="el-icon-edit" v-if="ownBlog">
              <router-link :to="{name: 'BlogEdit', params: {blogId: blog.id}}" >
                编辑
              </router-link>
            </el-link>
          </div>
          <br><br>
          <div class="markdown-body" v-html="blog.content"></div>
          <br><br><br><br><br><br><br><br><br><br><br>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import 'github-markdown-css'
  import Header from "../components/Header";

  export default {
    name: "BlogDetail.vue",
    components: {Header},
    data() {
      return {
        blog: {
          id: "",
          title: "",
          content: ""
        },
        ownBlog: false
      }
    },
    created() {
      const blogId = this.$route.params.blogId
      console.log(blogId)
      const _this = this
      this.$axios.get('/blog/' + blogId).then(res => {
        const blog = res.data.data
        _this.blog.id = blog.id
        _this.blog.title = blog.title

        var MardownIt = require("markdown-it")
        var md = new MardownIt()

        var result = md.render(blog.content)
        _this.blog.content = result
        _this.ownBlog = (blog.userId === _this.$store.getters.getUser.id)

      })
    }
  }
</script>

<style scoped>

  .article-time{
    font-size: 13px;
  }
  .article-views{
    font-size: 13px;
  }
  .main{
    background-color: #F9F9F9;
    width: 96%;
    position: relative;
    left: 22px
  }

</style>