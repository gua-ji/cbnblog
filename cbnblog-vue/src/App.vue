<template>
  <div class="app">
    <el-row :gutter="20" style="height: 120px">
      <el-col :span="18" :offset="2" >
        <el-header>
          <Header></Header>
        </el-header>
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="16" :offset="4">
          <router-view></router-view>
      </el-col>
    </el-row>
    <myfooter></myfooter>
  </div>
</template>

<script>
import myfooter from "@/components/myfooter";
export default {
  name: 'app',
  data() {
    return {
      minHeight: 0,
      navBarFixed: false
    };
  },
  components: {myfooter},
  methods: {
    watchScroll() {
      var scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop
      //  当滚动超过 50 时，实现吸顶效果
      if (scrollTop > 50) {
        this.navBarFixed = true
      } else {
        this.navBarFixed = false
      }
    }
  },
  mounted() {
    let that = this
    that.minHeight = document.documentElement.clientHeight
    window.addEventListener('scroll', that.watchScroll)
    window.onresize = function () {
      that.minHeight = document.documentElement.clientHeight
    }
  }
}
</script>

<style scoped>
.app {
  font-family: "microsoft yahei";
}

#content {
  background-color: #eee5e5;
  padding: 100px 0;
}

</style>
