<template>
  <div class="page-container">
    <div class="page-content">
      <!-- 顶部 -->
      <div style="display: flex;align-items: center;margin: 20px 0 0 50px;">
        <h2 style="color:black;margin-left: 50px;text-align: center; font-size: 30px; font-weight:1000;color:#777777;">每日健康登记管理系统</h2>
      </div>
      <!-- 中部 -->
      <div style="margin-top: 60px;margin-left: 80px; display: flex;align-items: center;">
        <!-- 图片 -->
        <div>
          <img
            src="../../assets/LoginPage/eduline.png"
            width="700px"
          />
        </div>
        <!-- 表单 -->
        <div>
          <div style="height: 380px;width: 500px;border: solid 1px gray;margin-bottom: 50px;margin-left: 100px; border-radius: 20px;box-shadow:0px 0px 20px rgb(161, 244, 250);background-color: white;">
            <div style="text-align: center;margin-top: 50px;">
              <h3 style="text-align: center; font-size: 20px; font-weight:1000;">欢迎登录每日健康登记管理系统</h3>
            </div>
            <el-form
              :model="form"
              status-icon
              :rules="rules"
              ref="loginForm"
              label-width="100px"
              class="loginForm"
              style="width: 85%;margin-top: 50px;text-align: center;"
            >
              <el-form-item label="账号">
                <el-input v-model="form.user_id"></el-input>
              </el-form-item>
              <el-form-item label="密码">
                <el-input
                  v-model="form.user_password"
                  show-password
                ></el-input>
              </el-form-item>
              <el-form-item style="margin-top: 50px;">
                <el-button
                  type="success"
                  @click.prevent="submitLogin()"
                  native-type="submit"
                >登录</el-button>
              </el-form-item>
            </el-form>
          </div>
        </div>
      </div>
      <!-- 底部 -->
      <div class="footer">
        <div>关于我们 <span>|</span> 联系我们</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      form: {
        user_id: "",
        user_password: "",
      },
      rules: {},
    };
  },
  computed: {},
  created() {},
  mounted() {},
  methods: {
    submitLogin() {
      this.$axios({
        method: "GET",
        url: "http://localhost:8181/login",
        params: {
          user_id: this.form.user_id,
          user_password: this.form.user_password,
        },
      }).then((response) => {

        if (response.data[0] == null) {
          alert("用户名或密码错误");
        } else {
          console.log(response.data);
          this.$store.dispatch("setUserNameAsy", response.data[0]);
          this.$store.dispatch("setRoleAsy", response.data[1]);
          this.$store.dispatch("setUserIdAsy", response.data[2]);
          this.$router.replace({ path: "/home" });
        }
      });
    },
  },
};
</script>

<style>
.footer{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin: 1vh;
  width: 95%;
  font-size: 12px;
  color: #777;
}
.footer div{
  margin: 0.5vh;
}
.page-container {
  position: relative;
  height: 100vh;
  width: 100vw;
  flex: 1 1 0%;
}

.visual-bg {
  position: absolute;
  left: 0;
  top: 0;
  right: 0;
  bottom: 0;
  z-index: 0;
  opacity: 85%;
}

.visual-bg > img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.page-content {
  position: relative;
  z-index: 3;
}
</style>