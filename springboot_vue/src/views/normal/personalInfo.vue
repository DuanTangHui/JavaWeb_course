<template>
  <div>
    <div class="headerText">
      <h2 style="color: #777777;">个人信息</h2>
    </div>

    <div class="personInfo" style="margin: 0 5rem;">
      <el-descriptions class="margin-top" :data="personInfoData"  title="详细信息" :column="2" border>
        <template slot="extra">
          <el-button type="primary" icon="el-icon-edit" size="medium" @click="handleUpdate()">操作</el-button>
        </template>
        <el-descriptions-item>
          <template slot="label"> ID </template>
          {{personInfoData.user_id}}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label"> 姓名</template>
          {{personInfoData.name}}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label"> 学院</template>
          {{personInfoData.academy}}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label"> 健康状况 </template>
          {{personInfoData.status}}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label"> 工作状态</template>
          {{personInfoData.work_status}}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label"> 是否外出</template>
          {{personInfoData.go_out}}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label"> 未登记次数</template>
          {{personInfoData.no_register_num}}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label"> 最后登记时间</template>
          {{personInfoData.last_register_time}}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label"> 备注</template>
          {{personInfoData.remark}}
        </el-descriptions-item>
      </el-descriptions>
    </div>

<!--    修改个人信息页面-->
    <el-dialog
        :title="dialogStatus"
        :visible.sync="dialogVisible"
        width="30%"
        ref="editForm"
        :before-close="handleClose">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
<!--        <el-form-item label="用户ID" prop="user_id">-->
<!--          <el-input v-model="form.user_id" readonly ></el-input>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="姓名" prop="name">-->
<!--          <el-input v-model="form.name" readonly></el-input>-->
<!--        </el-form-item>-->
        <el-form-item label="用户名" prop="user_name">
          <el-input v-model="form.user_name "></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input type="password" v-model="form.user_password"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitForm()">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import {getPersonInfoByID, updatePersonalInfo} from "@/network/person";

export default {
  name: "personalInfo",
  data(){
    return{
      id: '',
      personInfoData:{
        user_id:'',
        user_name:'',
        name:'',
        academy:'',
        health_status:'',
        work_status:'',
        go_out:'',
        no_register_num:'',
        last_register_time:'',
        remark:''
      },
      dialogVisible:false,
      dialogStatus:"修改个人信息",
      form:{
        user_id: '',
        user_name:'',
        user_password:'',
      },
      rules:{
        password:[
          { required: true, message: '请输入密码', trigger: 'blur' }
        ]
      }
    }
  },
  methods:{
    getPersonInfo(){
      let para ={user_id : this.id}
      getPersonInfoByID(para).then((res) => {
        this.personInfoData = res;
      })
    },
    handleUpdate(){
      this.dialogVisible = true;
      this.form = {
        user_id: this.id,
        user_name : this.personInfoData.user_name,
        password: '',
      }
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
    },
    submitForm(){
      this.$refs.form.validate((valid) => {
        if (valid) {
          let para = this.form;
          console.log(para);
          updatePersonalInfo(para).then((res) => {
            console.log(res);
            if(res == 'success'){
              this.$message({
                message:"提交成功",
                type:"success"
              });
              // this.$refs["form"].resetFields(); //这样写了我的para打印不出来
              this.dialogVisible = false;
            }
          })
        }
        else {
          console.log('error submit!!');
          return false;
        }
      });
    }
  },
  created() {
    this.id = this.$store.getters.getUserId;
    this.getPersonInfo();

  }
}
</script>

<style scoped>

</style>