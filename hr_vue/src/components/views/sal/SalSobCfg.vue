<template>
  <div>
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>工资账套设置</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card shadow="always" style="margin-top: 15px;">
      <el-table :data="tableData" border style="width: 100%">
        <el-table-column prop="name" label="姓名" width="180">
        </el-table-column>
        <el-table-column prop="workid" label="工号" width="180">
        </el-table-column>
        <el-table-column prop="email" label="电子邮箱"> </el-table-column>
        <el-table-column prop="phone" label="电话号码"> </el-table-column>
        <el-table-column label="所属部门">
          <template slot-scope="scope">
            {{ scope.row.department.name }}
          </template>
        </el-table-column>
        <el-table-column label="账套">
          <template slot-scope="scope">
            <el-tooltip placement="right" effect="light" :enterable="false">
              <div slot="content" v-if="scope.row.salary">
                <span v-if="scope.row.salary"
                  >启用时间：{{
                    scope.row.salary.createdate | dateFormat
                  }}</span
                ><br />
                <span v-if="scope.row.salary"
                  >基础工资：{{ scope.row.salary.basicsalary }}</span
                ><br />
                <span v-if="scope.row.salary"
                  >午餐补助：{{ scope.row.salary.lunchsalary }}</span
                ><br />
                <span v-if="scope.row.salary"
                  >交通补助：{{ scope.row.salary.trafficsalary }}</span
                ><br />
                <span v-if="scope.row.salary"
                  >奖金：{{ scope.row.salary.bonus }}</span
                ><br />
                <span v-if="scope.row.salary"
                  >养老金基数：{{ scope.row.salary.pensionbase }}</span
                ><br />
                <span v-if="scope.row.salary"
                  >养老金比率：{{ scope.row.salary.pensionper }}</span
                ><br />
                <span v-if="scope.row.salary"
                  >医保基数：{{ scope.row.salary.medicalbase }}</span
                ><br />
                <span v-if="scope.row.salary"
                  >医保比率{{ scope.row.salary.medicalper }}</span
                ><br />
                <span v-if="scope.row.salary"
                  >公积金基数：{{ scope.row.salary.accumulationfundbase }}</span
                ><br />
                <span v-if="scope.row.salary"
                  >公积金比率：{{ scope.row.salary.accumulationfundper }}</span
                ><br />
              </div>
              <el-tag hit effect="plain" size="small">
                <i class="el-icon-bank-card"></i>
                <span
                  v-text="
                    scope.row.salary !== null
                      ? scope.row.salary.name
                      : '还未添加'
                  "
                ></span>
              </el-tag>
            </el-tooltip>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-dropdown placement="bottom-end" @command="changeMenu">
              <el-button type="danger" size="mini"
                >修改工资账套<i class="el-icon-arrow-down el-icon--right"></i
              ></el-button>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item
                  :command="scope.row.id + '-' + item.id"
                  v-for="(item, idx) in salarySalSobData"
                  :key="idx"
                  >{{ item.name }}</el-dropdown-item
                >
              </el-dropdown-menu>
            </el-dropdown>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page.sync="pageNum"
        :page-size="10"
        layout="total, prev, pager, next"
        :total="total"
        style="margin-top: 15px;"
      >
      </el-pagination>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      tableData: [],
      pageNum: 1,
      pageSize: 10,
      total: 0,
      salarySalSobData: []
    }
  },
  methods: {
    changeMenu(command) {
      const arr = command.split('-')
      this.getRequest('/sal/sobcfg/' + arr[0] + '/' + arr[1]).then(resp => {
        this.$message(resp.data.msg)
        this.init()
      })
    },
    sureSalMenu(item) {
      console.log(item)
    },
    handleSizeChange(newSize) {
      this.pageSize = newSize
      this.init()
    },
    handleCurrentChange(newNum) {
      this.pageNum = newNum
      this.init()
    },
    init() {
      this.getRequest(
        '/sal/sobcfg/' +
          '?pageNum=' +
          this.pageNum +
          '&pageSize=' +
          this.pageSize
      ).then(res => {
        this.tableData = res.data.employees
        this.total = res.data.total
      })
    },
    init2() {
      this.getRequest('/sal/sob/').then(resp => {
        console.log(resp)
        this.salarySalSobData = resp.data.obj
      })
    }
  },
  created() {
    this.init()
    this.init2()
  }
}
</script>

<style lang="less"></style>
