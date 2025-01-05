<template>
  <div>
    <div class="headerText">
      <h2 style="color: #777777;">未登记人员信息</h2>
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
      </el-row>
    </div>

    <div class="dataTable">
      <el-table :data="unRegisterTableData" border style="width: 100%">
        <el-table-column prop="user_id" label="用户ID" align="center">
        </el-table-column>
        <el-table-column prop="academy" label="学院" align="center">
        </el-table-column>
        <el-table-column prop="name" label="姓名" align="center">
        </el-table-column>
        <el-table-column prop="no_register_num" label="未登记次数" align="center">
        </el-table-column>
        <el-table-column prop="last_register_time" label="最后一次登记时间" align="center">
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

  </div>
</template>

<script>
import {getAllStuInfo} from "@/network/controller";

export default {
  name: "unRegisterInfo",
  data(){
    return{
      unRegisterTableData:[],
      total:0,
      page_size:8,
      currentPage:1,
      filters:{
        user_id:'',
        status:null
      },
      optionsOfEnabled:[
        {valueOfEnabled: true,
          label:"是"},
        {valueOfEnabled: false,
          label: "否"}
      ],

    }
  },
  methods: {
    page(currentPage){
      getAllStuInfo(currentPage,this.page_size).then((res) => {
        console.log(res)
        this.unRegisterTableData = [];
        this.unRegisterTableData = res.content;
        this.total = res.totalElements;
      })
    },
    searchUser(){},

  },
  created() {
    this.page(1);
  }
}
</script>

<style scoped>

</style>