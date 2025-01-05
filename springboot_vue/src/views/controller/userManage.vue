<template>
  <div>
    <div class="headerText">
      <h2 style="color: #777777;">用户管理</h2>
    </div>

    <div class="functionBar">
      <el-row v-model="filters" style="margin: 5px">
        <el-input
            v-model="filters.user_id"
            placeholder="用户ID"
            clearable
            @clear="searchUser()"
            style="width: 150px;margin-right: 10px">
        </el-input>
        <el-select
            v-model="filters.status"
            placeholder="是否启用"
            clearable
            @clear="searchUser()"
            style="width: 150px;margin-right: 10px">
          <el-option
              v-for="item in optionsOfEnabled"
              :key="item.valueOfEnabled"
              :label="item.label"
              :value="item.valueOfEnabled">
          </el-option>
        </el-select>
        <el-button type="primary" @click="searchUser()">搜索</el-button>
        <el-button type="primary" @click="handleAdd">新增</el-button>
      </el-row>
    </div>

    <div class="dataTable">
      <el-table :data="UserTableData" border style="width: 100%">
        <el-table-column prop="user_id" label="用户ID" align="center">
        </el-table-column>
        <el-table-column prop="academy" label="学院" align="center">
        </el-table-column>
        <el-table-column prop="name" label="姓名" align="center">
        </el-table-column>
        <el-table-column prop="user_name" label="用户名" align="center">
        </el-table-column>
        <el-table-column prop="status" label="状态" align="center">
          <template slot-scope="scope">
            <el-switch
                v-model="scope.row.status"
                active-color="#13ce66"
                inactive-color="#CCCCCC"
                :active-value=true
                :inactive-value=false
                @change="changeStatus(scope.$index,scope.row)"
            >
            </el-switch>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button
                type="primary"
                v-if="scope.row.status == true"
                @click="handleUpdate(scope.$index, scope.row)">编辑
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <div class="block">
      <el-pagination
          layout="prev, pager, next"
          :page-size = page_size
          @current-change = "page(currentPage)"
          :current-page.sync="currentPage"
          :total = total>
      </el-pagination>
    </div>

<!--    新增、修改用户的表单form-->
    <el-dialog
        :title="dialogStatus"
        :visible.sync="dialogVisible"
        width="30%"
        ref="editForm"
        :before-close="handleClose">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户ID" prop="user_id">
          <el-input v-if="dialogStatus=='编辑用户'" v-model="form.user_id" readonly ></el-input>
          <el-input v-if="dialogStatus=='新增用户'" v-model="form.user_id" ></el-input>
        </el-form-item>
        <el-form-item label="用户名" prop="user_name">
          <el-input v-model="form.user_name "></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="name">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="学院" prop="academy">
          <el-select v-model="form.academy" placeholder="请选择所在学院">
            <el-option label="计算机科学与技术学院" value="计算机科学与技术学院"></el-option>
            <el-option label="华文学院" value="华文学院"></el-option>
            <el-option label="国关学院" value="国关学院"></el-option>
            <el-option label="信息工程学院" value="信息科学与工程学院"></el-option>
            <el-option label="建筑学院" value="建筑学院"></el-option>
            <el-option label="土木工程学院" value="土木工程学院"></el-option>
            <el-option label="材料学院" value="材料学院"></el-option>
            <el-option label="音舞学院" value="音舞学院"></el-option>
            <el-option label="机电学院" value="机电学院"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="状态">
          <el-switch v-model="form.status"></el-switch>
        </el-form-item>
        <el-form-item label="备注">
          <el-input type="textarea" v-model="form.remark"></el-input>
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
import {addStuInfo, getAllStuInfo, searchStuInfo, updateStuInfo} from "@/network/controller";

export default {
  name: "userManage",
  data() {
    return {
      UserTableData: [],
      total:0,
      page_size:8,
      currentPage:1,
      filters:{
        user_id:'',
        status: null,
      },
      optionsOfEnabled:[
        {valueOfEnabled: true,
          label:"是"},
        {valueOfEnabled: false,
          label: "否"}
      ],
      dialogVisible:false,
      dialogStatus:'',
      form: {
        user_id: '',
        user_name : '',
        name : '',
        academy : '',
        status : false,
        remark : ''
      },
      rules: {
        user_id: [
          { required: true, message: '请输入用户ID', trigger: 'blur' },
          { min: 4, max: 4, message: '长度必须为4', trigger: 'blur' }
        ],
        user_name: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
        ],
        name: [
          { required: true, message: '请输入姓名', trigger: 'blur' },
        ],
        academy: [
          { required: true, message: '请选择学院', trigger: 'change' }
        ],
      }
    }
  },
  methods: {
    page(currentPage){
      getAllStuInfo(currentPage,this.page_size).then((res) => {
        this.UserTableData = [];
        this.UserTableData = res.content;
        this.total = res.totalElements;
      })
    },
    changeStatus(index,row){
    },
    searchUser(){
      let para = {user_id: this.filters.user_id,
        status: this.filters.status,
      }
      console.log(para);
      searchStuInfo(para).then(res =>{
        console.log(res);
        this.UserTableData = []
        this.UserTableData = res;
        console.log(this.userlist);
      })
    },
    handleAdd(){
      this.dialogStatus = '新增用户';
      this.dialogVisible = true;
      this.form = {
        user_id: '',
        user_name : '',
        name : '',
        academy : '',
        status : false,
        remark : ''
      }
      if (this.$refs["form"]!==undefined) {
        console.log("resetForm");
        this.$refs["form"].resetFields();
      }
    },
    handleUpdate(index,row){
      this.dialogStatus = "编辑用户";
      this.dialogVisible = true;
      this.form = row;
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
    },
    submitForm(){
      const _this = this;
      this.$refs.form.validate((valid) => {
        if (valid) {
            let para = this.form;
            console.log(para);
            addStuInfo(para).then((res) => {
              console.log(res);
              if(res == 'success'){
                this.$message({
                  message:"提交成功",
                  type:"success"
                });
                this.$refs["form"].resetFields(); //这样写了我的para打印不出来
                this.dialogVisible = false;
                this.page(_this.currentPage);
              }
            })
        }
        else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    updateForm(){
      const _this = this;
      this.$refs.form.validate((valid) => {
        if (valid) {
          let para = this.form;
          console.log(para);
          updateStuInfo(para).then((res) => {
            console.log(res);
            if(res == 'success'){
              this.$message({
                message:"提交成功",
                type:"success"
              });
              this.$refs["form"].resetFields(); //这样写了我的para打印不出来
              this.dialogVisible = false;
              this.page(_this.currentPage);
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
    this.page(1);
  }
}
</script>

<style scoped>

</style>