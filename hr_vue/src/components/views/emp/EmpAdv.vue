<template>
  <div>
    <el-breadcrumb separator='/' style='margin-bottom:15px;'>
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>高级资料</el-breadcrumb-item>
    </el-breadcrumb>
    <el-tabs type='border-card' @tab-click='tabChange'>
      <el-tab-pane>
        <span slot='label'><i class='el-icon-bank-card'></i> 奖惩资料</span>
        <el-table :data='employeeC' style='width: 100%'>
          <el-table-column label='#' type='index' width='50'>
          </el-table-column>
          <el-table-column label='奖惩日期' width='200'>
            <template slot-scope='scope'>
              {{ scope.row.ecdate | dateFormat }}
            </template>
          </el-table-column>
          <el-table-column prop='ecDate' label='奖惩分数'>
            <template slot-scope='scope'>
              <el-tag v-if='scope.row.ecpoint >= 0' type='success' size='small' effect='dark'>
                +{{ scope.row.ecpoint }}
              </el-tag>
              <el-tag v-if='scope.row.ecpoint < 0' type='danger' size='small' effect='dark'>
                {{ scope.row.ecpoint }}
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column prop='ecreason' label='奖惩原因'>
            <template slot-scope='scope'>
              <span v-if='scope.row.ecpoint >=0'>
                {{ scope.row.ecreason }}
                <i class='fa fa-thumbs-up' style='color: red;'></i>
              </span>
              <span v-else>
                {{ scope.row.ecreason }}
                <i class='fa fa-thumbs-down'></i>
              </span>
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>
      <el-tab-pane>
        <span slot='label'><i class='el-icon-thumb'></i> 培训资料</span>

        <el-table :data='employeeTrain' style='width: 100%'>
          <el-table-column label='#' type='index' width='50'>
          </el-table-column>
          <el-table-column label='奖惩日期' width='200'>
            <template slot-scope='scope'>
              {{ scope.row.traindate | dateFormat }}
            </template>
          </el-table-column>
          <el-table-column prop='traincontent' label='培训内容' width='120'>
          </el-table-column>
        </el-table>
      </el-tab-pane>
      <el-tab-pane>
        <span slot='label'><i class='el-icon-shopping-bag-1
'></i>
          考评资料</span>
        <el-table :data='employeeAppraise' style='width: 100%'>
          <el-table-column label='#' type='index' width='50'>
          </el-table-column>
          <el-table-column label='培训日期' width='200'>
            <template slot-scope='scope'>
              {{ scope.row.appdate | dateFormat }}
            </template>
          </el-table-column>
          <el-table-column prop='appcontent' label='考评内容' width='120'>
          </el-table-column>
          <el-table-column prop='appresult' label='考评结果' width='130'>
            <template slot-scope='scope'>
              <span v-if="scope.row.appresult==='优秀'">
                {{ scope.row.appresult }}
                <i class='el-icon-star-on' style='color: red;'></i>
              </span>
              <span v-else>
                {{ scope.row.appresult }}
              </span>
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>
      <el-tab-pane>
        <span slot='label'><i class='fa fa-yen'></i> 调薪资料</span>
        <el-table :data='employeeAdjustSalary' style='width: 100%'>
          <el-table-column label='#' type='index' width='50'>
          </el-table-column>
          <el-table-column label='调薪日期' width='200'>
            <template slot-scope='scope'>
              {{ scope.row.asdate | dateFormat }}
            </template>
          </el-table-column>
          <el-table-column prop='beforesalary' label='调前薪资' width='120'>
          </el-table-column>
          <el-table-column label='调后薪资' width='120'>
            <template slot-scope='scope'>
              {{ scope.row.aftersalary }}
              <span v-if='scope.row.aftersalary >= scope.row.beforesalary'>
                <i v-if='' class='fa fa-arrow-circle-up' style='color: green;'></i>
              </span>
              <span v-else>
                <i class='fa fa-arrow-circle-down' style='color: red;'></i>
              </span>
            </template>
          </el-table-column>
          <el-table-column prop='reason' label='调薪原因' width='120'>
          </el-table-column>
        </el-table>
      </el-tab-pane>
      <el-tab-pane>
        <span slot='label'><i class='el-icon-chat-square'></i> 调动资料</span>
        <el-table :data='employeeMove' style='width: 100%'>
          <el-table-column label='#' type='index' width='50'>
          </el-table-column>
          <el-table-column label='调动日期' width='200'>
            <template slot-scope='scope'>
              {{ scope.row.removedate | dateFormat }}
            </template>
          </el-table-column>
          <el-table-column prop='oldDepartName' label='调前部门' width='120'>
          </el-table-column>
          <el-table-column prop='oldJoblevelName' label='调前职位' width='120'>
          </el-table-column>
          <el-table-column prop='reason' label='调薪原因' width='180'>
          </el-table-column>
          <el-table-column prop='newDepartName' label='调后部门' width='120'>
          </el-table-column>
          <el-table-column prop='newJoblevelName' label='调后职位' width='120'>
          </el-table-column>
          <el-table-column prop='remark' label='备注' width='120'>
          </el-table-column>
        </el-table>
      </el-tab-pane>
      <el-tab-pane>
        <span slot='label'><i class='el-icon-chat-dot-round'></i> 工资信息</span>
        <el-table :data='empSalaryInfo' stripe>
          <el-table-column label='#' type='index' width='50'>
          </el-table-column>
          <el-table-column prop='salaryName' label='账套名称' width='120'>
          </el-table-column>
          <el-table-column label='月份' width='50'>
            <template slot-scope='scope'>
              {{ scope.row.salaryTime | DateFilterMonth }}
            </template>
          </el-table-column>
          <el-table-column prop='allSalary' label='工资' width='120'>
          </el-table-column>
        </el-table>
      </el-tab-pane>
      <el-tab-pane>
        <span slot='label'><i class='el-icon-guide'></i> 其他信息</span>
        其他信息
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
export default {
  data() {
    return {
      employeeC: [],
      employeeTrain: [],
      employeeAppraise: [],
      employeeAdjustSalary: [],
      employeeMove: [],
      empSalaryInfo: []
    }
  },
  methods: {
    tabChange(tab) {
      if (tab.index === '0') {
        this.init()
      } else if (tab.index === '1') {
        this.showEmployeeTrain()
      } else if (tab.index === '2') {
        this.showEmployeeAppraise()
      } else if (tab.index === '3') {
        this.showEmployeeAdjustSalary()
      } else if (tab.index === '4') {
        this.showEmployeeMove()
      } else if (tab.index === '5') {
        this.showSalaryMonth()
      }
    },
    init() {
      this.getRequest('/emp/adv/c/').then(res => {
        this.employeeC = res.data.obj
      })
    },
    showEmployeeTrain() {
      this.getRequest('/emp/adv/train/').then(res => {
        this.employeeTrain = res.data.obj
      })
    },
    showEmployeeAppraise() {
      this.getRequest('/emp/adv/app/').then(res => {
        this.employeeAppraise = res.data.obj
      })
    },
    showEmployeeAdjustSalary() {
      this.getRequest('/emp/adv/adj/').then(res => {
        this.employeeAdjustSalary = res.data.obj
      })
    },
    showEmployeeMove() {
      this.getRequest('/emp/adv/remove/').then(res => {
        this.employeeMove = res.data.obj
        console.log(res.data.obj)
      })
    },
    showSalaryMonth() {
      this.getRequest('/emp/adv/salaryInfo/').then(res => {
        this.empSalaryInfo = res.data.obj
        console.log(res)
      })
    }
  },
  created() {
    this.init()
  },
  filters: {
    DateFilterMonth: function(time) {
      const t = new Date(time)
      return t.getMonth() + 1
    }
  }
}
</script>

<style lang='less'></style>
