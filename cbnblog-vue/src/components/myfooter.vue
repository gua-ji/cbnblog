<template>
  <div class="f-footer">
    <el-main class="main">
      <el-row>
        <el-col :span="8" class="item-col">
          <div class="title">{{$t('footer.expressLane')}}</div>
          <div class=""><a href="#" class="out-link">{{$t('footer.gitee')}}</a></div>
          <div class=""><a href="#" class="out-link">CSDN</a></div>
          <div class=""><a href="#" class="out-link">{{$t('footer.admin')}}</a></div>
        </el-col>
        <el-col :span="8" class="item-col weside">
          <div class="item">
            <i class="el-icon-caret-right"></i>
            本站已稳定运行23天
          </div>
          <div class="item">
            <i class="el-icon-caret-right"></i>
            1247909630@qq.com
          </div>
          <div class="item">
            <i class="el-icon-caret-right"></i>
            © guaji-博客
          </div>
        </el-col>
      </el-row>
    </el-main>
  </div>
</template>

<script>
export default {
  name: "myfooter"
}
</script>

<style scoped>
.f-footer{
  padding: 20px;
  text-align: center;
  color: #fff;
  background-color: #555;
  position: relative;
  bottom: 0px;
}
.main{
  width: 70%;
  margin: 0 auto;
  color: #ccc;
}
.out-link{
  text-decoration: none;
  color: #ccc;
}
.out-link:hover{
  color: #ffffff;
}
.item-col{
  text-align: left;
}
.item-col.weside{
  font-size: 14px;
}
.item-col .title{
  color: #ddd;
  margin-bottom: 10px;
}
.item span{
  margin-right: 5px;
}
</style>