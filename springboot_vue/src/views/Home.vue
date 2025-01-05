<template>
  <div class="main">
    <!--左侧边菜单栏-->
    <nav-menu :navHeigth="getHeight"></nav-menu>
    <!-- 右侧内容区域 路由实现跳转 -->
    <div class="right-container">
      <HeaderBar />
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import NavMenu from "../views/NavMenu.vue";
import HeaderBar from "../components/HeaderBar.vue";

export default {
  components: {
    NavMenu,
    HeaderBar,
  },
  // created() {
  //   this.userName = this.$route.query.userName;
  //   // console.log(document.body.clientHeight);
  // },
  computed:{
    getHeight(){
      return Math.max(document.documentElement.scrollHeight, document.documentElement.clientHeight);
      // return document.body.scrollHeight;
    }
  }
};
</script>

<style>
.main {
  display: flex;
  width: 100vw;
  height: 100vh;
}
.right-container {
  padding: 15px;
  /* flex: 1; */
  width: calc(100% - 215px);
  box-sizing: border-box;
  /* min-width: 990px; */
}
</style>