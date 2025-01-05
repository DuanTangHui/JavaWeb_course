<template>
  <div>
    <div class="headerText">
      <h2 style="color: #777777;">历史登记信息</h2>
    </div>
    <div>
      <el-table :data="historyData" border style="width: 100%">
        <el-table-column prop="user_id" label="用户id" align="center">
        </el-table-column>
        <el-table-column prop="academy" label="学院"align="center">
        </el-table-column>
        <el-table-column prop="name" label="姓名" align="center">
        </el-table-column>
        <el-table-column prop="health_status" label="健康状况" align="center" :formatter="formatBoolean">
        </el-table-column>
        <el-table-column prop="temperature" label="体温" align="center">
        </el-table-column>
        <el-table-column prop="go_out" label="是否外出" align="center" :formatter="formatBoolean">
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import {getHistoryById} from "@/network/person";

export default {
  name: "historyRegister",
  data(){
    return {
      id: '',
      historyData:[],
    }
  },
  methods:{
    getHistory(){
      let para ={user_id : this.id}
      getHistoryById(para).then((res) => {
        this.historyData = res;
      })
    },
    /*布尔值格式化：cellValue为后台返回的值*/
    formatBoolean: function (row, column, cellValue) {
      var ret = '' ; //你想在页面展示的值
      if (cellValue) {
        ret = "是" ; //根据自己的需求设定
        console.log("shide ")
      } else {
        ret = "否";
      }
      return ret;
    },
  },
  created() {
    this.id = this.$store.getters.getUserId;
    this.getHistory();
  }
}
</script>

<style scoped>

</style>