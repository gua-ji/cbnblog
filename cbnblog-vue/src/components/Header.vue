<template>
  <div class="head">
    <el-row id="artList" type="flex" justify="center">
      <el-col :span="16">
    <el-menu :default-active="activeIndex" class="d-flex" mode="horizontal" :router="true" type="flex"
             background-color="#F9F9F9"
             text-color="#000000"
             active-text-color="#000000">
      <span class="mr-auto" >CBN的blog</span>
      <img :src="imgUrl" class="picture">
      <el-menu-item class="word" index="/blogs">首页</el-menu-item>
      <el-menu-item class="word" index="/archive">归档</el-menu-item>
      <el-menu-item class="word" index="/about">关于</el-menu-item>
      <el-submenu class="word">
        <template slot="title">{{$t("header.language")}}</template>
        <el-menu-item @click="toggleLang('zh')">{{$t("header.chinaese")}}</el-menu-item>
        <el-menu-item @click="toggleLang('en')">{{$t("header.english")}}</el-menu-item>
      </el-submenu>
      <el-menu-item class="word" v-if="hasLogin" index="/admin">个人空间</el-menu-item>
      <el-button  class="word" v-if="hasLogin" @click="logout" type="text">退出登录</el-button>
      <el-menu-item class="word" v-if="!hasLogin" index="/login">登录</el-menu-item>
    </el-menu>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "Header",
  data() {
    return {
      imgUrl: '',
      activeIndex: '1',
      user: {
        username: '',
        avatar: ''
      },
      hasLogin: false
    };
  },
  methods: {
    toggleLang(lang) {
      if(lang === 'zh') {
        localStorage.setItem('locale', 'zh')
        this.$i18n.locale = localStorage.getItem('locale')
        this.$message({
          message: '切换为中文！',
          type: 'success'
        })
      } else if(lang === 'en') {
        localStorage.setItem('locale', 'en')
        this.$i18n.locale = localStorage.getItem('locale')
        this.$message({
          message: 'Switch to English!',
          type: 'success'
        })
      }
    },
    logout() {
      const _this = this
      _this.$axios.get("/logout", {
        headers: {
          "Authorization": localStorage.getItem("token")
        }
      }).then(res => {
        _this.$store.commit("REMOVE_INFO")
        _this.$router.go(0)
      })
    },
    },
  created() {
    if(this.$store.getters.getUser.username) {
      this.hasLogin = true
      this.user.username = this.$store.getters.getUser.username
      this.user.avatar = this.$store.getters.getUser.avatar
    }
    this.imgUrl=require("@/assets/timg.png")
  }
}
</script>

<style scoped>
.mr-auto{
  position: relative;
  left: 30px;
  top: 20px;
}
.picture{
  margin: 0 auto;
  position: relative;
  right: 30px;
  width: 60px;
  height: 60px
}
.head {
  position:fixed;
  top:0;
  left: -153px;
  z-index:999;
  width: 120%;
}
.word{
  position:relative;
  right: 20px
}
</style>