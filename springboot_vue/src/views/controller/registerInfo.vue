<template>
  <div>
    <div class="headerText">
      <h2 style="color: #777777;">所有人员信息</h2>
    </div>

    <div>
      <el-table :data="tableData" border style="width: 100%">
        <el-table-column prop="user_id" label="用户id" align="center">
        </el-table-column>
        <el-table-column prop="academy" label="学院"align="center" width="200px">
        </el-table-column>
        <el-table-column prop="name" label="姓名" align="center">
        </el-table-column>
        <el-table-column prop="health_status" label="健康状况" align="center" width="100px" :formatter="formatBoolean">
        </el-table-column>
        <el-table-column prop="temperature" label="体温" width="100px" align="center">
        </el-table-column>
        <el-table-column prop="go_out" label="是否外出" align="center" width="100px" :formatter="formatBoolean">
        </el-table-column>
        <el-table-column prop="fill_time" label="填报时间" align="center">
        </el-table-column>
        <el-table-column prop="work_status " label="工作状态" align="center">
        </el-table-column>
        <el-table-column prop="remark" label="备注" align="center">
        </el-table-column>
      </el-table>
    </div>

    <div class="block">
      <el-pagination
          layout="prev, pager, next"
          :page-size = page_size
          @current-change = "page"
          :total = total>
      </el-pagination>
    </div>
  </div>
</template>

<script>
import {getAllStuInfo} from "@/network/controller";

export default {
  name: 'registerInfo',
  data() {
    return {
      tableData: [],
      total:0,
      page_size:8,
      formatter:''
    }
  },
  computed: {

  },
  methods: {
    handleClick(row) {
      console.log(row);
    },
    page(currentPage){
      getAllStuInfo(currentPage,this.page_size).then((res) => {
        this.tableData = [];
        this.tableData = res.content;
        this.total = res.totalElements;
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
    this.page(1)
  }
}
</script>

<style scoped>

</style>