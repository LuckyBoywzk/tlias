<template>
  <div>
    <!-- 导航栏 -->
    <el-col :span="12" style="width: 100%">
      <el-menu
        default-active="2"
        class="el-menu-vertical-demo"
        @open="handleOpen"
        @close="handleClose"
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#ffd04b"
        style="height: 678px;"
      >
        <el-menu-item index="1">
          <i class="el-icon-menu"></i>
          <span slot="title" style="padding-right: 52px"><router-link to="/home">首页</router-link></span>
        </el-menu-item>
        <el-submenu index="2">
          <template slot="title">
            <i class="el-icon-location"></i>
            <span>系统信息管理</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="1-1"><router-link to="/emp">员工管理</router-link></el-menu-item>
            <el-menu-item index="1-2"><router-link to="/dept">部门管理</router-link></el-menu-item>
          </el-menu-item-group>
        </el-submenu>
        <el-menu-item index="3" disabled>
          <i class="el-icon-document"></i>
          <span slot="title" style="padding-right: 43px">导航三</span>
        </el-menu-item>
        <el-menu-item index="4">
          <i class="el-icon-setting"></i>
          <span slot="title" style="padding-right: 54px"><router-link to="/chart">图表</router-link></span>
        </el-menu-item>
      </el-menu>
    </el-col>
  </div>
</template>

<script>
export default {
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
  },
};
</script>

<style>
a {
  text-decoration: none;
  color: white;
}

.router-link-active {
  color: inherit;
}
</style>