<template>
  <div class="headerBar">
    <i class="el-icon-user-solid"></i>
    <el-dropdown trigger="click">
      <span class="el-dropdown-link"
        >Hello,{{ userName }}
        <i class="el-icon-caret-bottom"></i>
      </span>
      <el-dropdown-menu :hide-on-click="false" slot="dropdown">
        <el-dropdown-item disabled>Hello,{{ userName }}</el-dropdown-item>
        <el-dropdown-item >账号信息</el-dropdown-item>
        <el-dropdown-item @click.native="exit()">退出登录</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </div>
</template>

<script>
export default {
  name: "HeaderBar",
  data() {
    return {
      // 接收后端传来的账户信息
    userName:'',
    };
  },
  methods: {
    exit() {
      this.$router.replace('/');
      sessionStorage.clear();
    },
  },
  created(){
    this.userName = this.$store.getters.getUserName;
  }
};
</script>

<style>
.headerBar {
  display: flex;
  justify-content: flex-end;
}
</style>
