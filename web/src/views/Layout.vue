<template>
  <div>
    <el-container>
      <el-header>tlias智能学习辅助系统</el-header>
      <el-container style="height: 678px">
        <el-aside width="200px">
            <NavBar></NavBar>
        </el-aside>
        <el-main>
            <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import NavBar from '../components/NavBar.vue'

export default {
    name: 'LayoutView',
    components: {
        NavBar
    }
};
</script>

<style>
  .el-header {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
    font-size: 20px;
  }
  
  .el-aside {
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
    line-height: 200px;
  }
  
  .el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
  }
</style>