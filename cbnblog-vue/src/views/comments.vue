<template>
  <div class="block">
    <Header></Header>
    <el-container>
      <el-aside width="200px" style="height: 500px">
        <Asider></Asider>
      </el-aside>
      <el-container>
        <el-footer>
        <el-row class="comments" v-for="(comment,index) in comments" :key="index">
          <el-card shadow="hover" class="mycard">
            <el-row class="art-info">
              <div class="art-time"><i class="el-icon-time"></i>{{ comment.created }}</div>
              <div class="art-time"><i class="el-icon-user"></i>{{ comment.nickName }}</div>
              <el-button @click="deleted(comment.id)" class="delete" type="text">删除</el-button>
            </el-row>
            <el-row class="art-body">
              <div class="side-abstract">
                  {{ comment.content }}
              </div>
            </el-row>

          </el-card>
        </el-row>
        </el-footer>
      </el-container>
    </el-container>
  </div>
</template>
<script>
import Asider from "@/components/asider";

export default {
  name: "comments",
  components: {Asider},
  data() {
    return {
      comments: []
    }
  },
  methods: {
    deleted(did) {
      this.$axios.delete('/comments/deleted/' + did).then(
          this.$router.go(0)
      )
    }
  },
  created() {
    this.$axios.get('/comments').then(res => {
          this.comments = res.data.data
        }
    )
  }
}
</script>

<style scoped>
.mycard{
  position: relative;
  top: 50px;
}
.art-info{
  font-size: 13px;
  color: #B3C0D1;
}
.delete{
  font-size: 13px;
  position: absolute;
  right: 0px;
  bottom: 0px;
}
</style>