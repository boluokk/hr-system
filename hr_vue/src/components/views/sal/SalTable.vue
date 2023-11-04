<template>
  <div>
    <el-breadcrumb separator='/' style='margin-bottom:15px;'>
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>工资表管理</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card shadow='always' style='margin-top: 15px;'>
      <el-input v-model='searchInput'
                placeholder='请输入员工工号'
                maxlength='8'
                show-word-limit style='width: 250px; margin-bottom: 15px;margin-right: 15px;' />
      <el-date-picker
        v-model='searchDate'
        type='datetime'
        placeholder='选择日期时间'
        align='right'
        value-format='yyyy-MM-dd HH:mm:ss'>
      </el-date-picker>
      <el-button type='primary' @click='searchCommit' style='margin-left: 15px'>搜索</el-button>
      <el-button type='primary' @click='printSalaryInfo' style='float: right'>打印工资单 <i
        class='el-icon-download'></i></el-button>
      <el-table :data='tableData' border style='width: 100%'>
        <el-table-column prop='employeeName' label='姓名'></el-table-column>
        <el-table-column prop='workId' label='工号'></el-table-column>
        <el-table-column prop='departmentName' label='部门名'></el-table-column>
        <el-table-column prop='jobLevelName' label='职称名'></el-table-column>
        <el-table-column label='账套'>
          <template slot-scope='scope'>
            <el-tooltip placement='right' effect='light' :enterable='false'>
              <div slot='content' v-if='scope.row.salary'>
                <span v-if='scope.row.salary'
                >启用时间：{{
                    scope.row.salary.createDate | dateFormat
                  }}</span
                ><br />
                <span v-if='scope.row.salary'
                >基础工资：{{ scope.row.salary.basicSalary }}</span
                ><br />
                <span v-if='scope.row.salary'
                >午餐补助：{{ scope.row.salary.lunchSalary }}</span
                ><br />
                <span v-if='scope.row.salary'
                >交通补助：{{ scope.row.salary.trafficSalary }}</span
                ><br />
                <span v-if='scope.row.salary'
                >奖金：{{ scope.row.salary.bonus }}</span
                ><br />
                <span v-if='scope.row.salary'
                >养老金基数：{{ scope.row.salary.pensionBase }}</span
                ><br />
                <span v-if='scope.row.salary'
                >养老金比率：{{ scope.row.salary.pensionPer }}</span
                ><br />
                <span v-if='scope.row.salary'
                >医保基数：{{ scope.row.salary.medicalBase }}</span
                ><br />
                <span v-if='scope.row.salary'
                >医保比率{{ scope.row.salary.medicalPer }}</span
                ><br />
                <span v-if='scope.row.salary'
                >公积金基数：{{ scope.row.salary.accumulationFundBase }}</span
                ><br />
                <span v-if='scope.row.salary'
                >公积金比率：{{ scope.row.salary.accumulationFundPer }}</span
                ><br />
              </div>
              <el-tag hit effect='plain' size='small'>
                <i class='el-icon-bank-card'></i>
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
        <el-table-column prop='salary.allSalary' label='工资'></el-table-column>
        <el-table-column prop='reward' label='奖励'></el-table-column>
        <el-table-column prop='punishment' label='罚款'></el-table-column>
        <el-table-column prop='tax' label='个人所得税'></el-table-column>
        <el-table-column prop='wagesPayable' label='应发工资'></el-table-column>
        <el-table-column label='操作'>
          <template slot-scope='scope'>
            <el-button type='danger' size='mini' @click='sendSalaryInfo(scope.row)'>发送工资单</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        @size-change='handleSizeChange'
        @current-change='handleCurrentChange'
        :current-page.sync='pageNum'
        :page-size='10'
        layout='total, prev, pager, next'
        :total='total'
        style='margin-top: 15px;'
      >
      </el-pagination>
    </el-card>
  </div>
</template>

<script>

import { formatDate } from '@/assets/js/MenuUtil'
import { downloadFiles } from '@/assets/js/AjaxUtil'

export default {
  data() {
    return {
      pageNum: 1,
      total: 0,
      pageSize: 10,
      searchInput: '',
      searchDate: formatDate(new Date()),
      tableData: null
    }
  },
  methods: {
    sendSalaryInfo(item) {
    },
    printSalaryInfo(item) {
      if (!this.searchDate) return this.$message.warning('时间不能为空')
      downloadFiles('/sal/table/export', {
        workId: this.searchInput,
        date: this.searchDate
      })
    },
    searchCommit() {
      this.init(true)
    },
    handleCurrentChange(newNum) {
      this.pageNum = newNum
      this.init()
    },
    handleSizeChange(newSize) {
      this.pageSize = newSize
      this.init()
    },
    init(t) {
      if (!this.searchDate) return this.$message.warning('时间不能为空!')
      this.postRequest('/sal/table/' + this.pageNum + '/' + this.pageSize, {
        workId: this.searchInput,
        date: this.searchDate
      }).then(res => {
        this.tableData = res.data.obj.list
        this.total = res.data.obj.total
        if (res.data.status === 200 && t) {
          this.$message.success('查询成功')
        }
      })
    }
  },
  created() {
    this.init()
  }
}
</script>

<style lang='less' scoped></style>
