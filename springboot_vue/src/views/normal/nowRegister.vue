<template>
  <div>
    <div class="headerText">
      <h2 style="color: #777777;">当前登记信息</h2>
    </div>
    <div class="submitForm" style="width: 700px">
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="用户ID" prop="user_id">
          <el-input v-model="form.user_id"  readonly ></el-input>
        </el-form-item>
        <el-form-item label="用户名" prop="user_name">
          <el-input v-model="form.user_name" readonly></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="name">
          <el-input v-model="form.name" readonly></el-input>
        </el-form-item>
        <el-form-item label="学院" prop="academy">
          <el-input v-model="form.academy" readonly></el-input>
        </el-form-item>
        <el-form-item label="健康状况">
          <el-switch v-model="form.health_status "></el-switch>
        </el-form-item>
        <el-form-item label="是否外出">
          <el-switch v-model="form.go_out"></el-switch>
        </el-form-item>
        <el-form-item label="工作状态">
          <el-input v-model="form.work_status "></el-input>
        </el-form-item>
        <el-form-item label="体温">
          <el-input v-model="form.temperature"></el-input>
        </el-form-item>
        <el-form-item label="备注">
          <el-input type="textarea" v-model="form.remark"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="submitForm()">提交</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>

import {getPersonInfoByID, submitPersonInfo} from "@/network/person";

export default {
  name: "nowRegister",
  data(){
    return{
      user_id:'',
      form: {
        user_id: '',
        user_name : '',
        name : '',
        academy : '',
        health_status: true,
        work_status: '',
        temperature: '',
        go_out: false,
        remark : ''
      },
    }
  },
  methods: {
    submitForm(){
      this.$refs.form.validate((valid) => {
        if (valid) {
          let para = this.form;
          console.log(para);
          submitPersonInfo(para).then((res) => {
            console.log(res);
            if(res == 'success'){
              this.$message({
                message:"提交成功",
                type:"success"
              });
              this.form.temperature = '';
              this.form.remark = '';
              this.form.work_status = '';
            }
          })
        }
        else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    getBaseInfo() {
      let para ={user_id : this.user_id}
      // console.log(para);
      getPersonInfoByID(para).then((res) => {
        // console.log(res);
        this.form.user_id = res.user_id;
        this.form.user_name =res.user_name;
        this.form.name  =res.name;
        this.form.academy  =res.academy
      })
    }
  },
  created() {
    this.user_id = this.$store.getters.getUserId;
    this.getBaseInfo();
  }
}
</script>

<style scoped>

</style>