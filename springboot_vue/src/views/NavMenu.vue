<template>
  <div class="nav-menu-wrapper">
    <el-menu
      class="nav-menu"
      @open="handleOpen"
      @close="handleClose"
      @select="handleSelect"
      background-color="#515A6E"
      text-color="#fff"
      active-text-color="#4A7EE4"
      :default-active="defaultActive"
      :unique-opened="true"
      router
      :style="{height:navHeigth}"
    >
      
      <!-- 登记信息 -->
      <el-submenu v-if = "this.role == '管理员'" index="1">
        <template slot="title">
          <i class="el-icon-s-data"></i>
          <span slot="title">管理员</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="registerInfo">登记信息</el-menu-item>
          <el-menu-item index="unRegisterInfo">未登记人员</el-menu-item>
          <el-menu-item index="userManage">用户管理</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <!-- 普通用户 -->
      <el-submenu v-if="this.role == '普通用户'" index="2">
        <template slot="title">
          <i class="el-icon-pie-chart"></i>
          <span slot="title">普通用户</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="personalInfo">个人信息</el-menu-item>
          <el-menu-item index="historyRegister">历史登记信息</el-menu-item>
          <el-menu-item index="nowRegister">当前登记信息</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
    </el-menu>
  </div>
</template>

<script>
export default {
  props:{
    navHeigth:{
      type:Number,
    }
  },
  name: "NavMenu",
  data() {
    return {
      defaultActive: '',
      role:'',
    };
  },
  methods: {
    handleOpen(key, keyPath) {
      // console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      // console.log(key, keyPath);
    },
    handleSelect(index) {
      this.defaultActive = index;
      this.$router.push({
        name: index,
      });
    },
  },
  created(){
    this.role = this.$store.getters.getRole;
    console.log(this.role);
  }
};
</script>

<style>
.nav-menu-wrapper {
  width: 15%;
  height: 100%;
  min-width: 215px;
}

.nav-menu {
  height: 100%;
}
</style>
