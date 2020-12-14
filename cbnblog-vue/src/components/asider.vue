<template>
  <div>
    <el-row class="tac" style="border: 1px #EBEEF5">
      <el-col :span="20">
        <h3>个人空间</h3>
        <el-menu
            default-active="/admin"
            :router="true"
            class="el-menu-vertical-demo">

          <el-menu-item index="/admin">
            <i class="el-icon-menu"></i>
            <span slot="title">文章管理</span>
          </el-menu-item>
          <el-menu-item index="/admin/blogadd">
            <i class="el-icon-document"></i>
            <span slot="title">新增文章</span>
          </el-menu-item>
          <el-menu-item index="/admin/gc">
            <i class="el-icon-setting"></i>
            <span slot="title">回收站</span>
          </el-menu-item>
          <el-menu-item index="/admin/comments">
            <i class="el-icon-setting"></i>
            <span slot="title">评论管理</span>
          </el-menu-item>
        </el-menu>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'asider',

}
</script>