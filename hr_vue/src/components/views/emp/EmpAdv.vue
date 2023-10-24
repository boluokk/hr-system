<template>
  <div>
    <el-breadcrumb separator='/' style='margin-bottom:15px;'>
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>高级资料</el-breadcrumb-item>
    </el-breadcrumb>
    <el-tabs type='border-card' @tab-click='tabChange'>
      <el-tab-pane>
        <span slot='label'><i class='el-icon-bank-card'></i> 奖惩资料</span>
        <el-table :data='employeeAdv' style='width: 100%'>
          <el-table-column label='#' type='index' width='50'>
          </el-table-column>
          <el-table-column prop='employee.name' label='员工名' width='100'>
            <template slot-scope='scope'>
              <span>{{ scope.row.employee.name }} </span>
              <el-tooltip placement='right'>
                <div slot='content'>
                  <div>
                    <span>名称: </span>
                    <span>{{ scope.row.employee.name }}</span>
                  </div>
                  <div>
                    <span>性别: </span>
                    <span>{{ scope.row.employee.gender }}</span>
                  </div>
                  <div>
                    <span>生日: </span>
                    <span>{{ scope.row.employee.birthday }}</span>
                  </div>
                  <div>
                    <span>身份证号: </span>
                    <span>{{ scope.row.employee.idCard }}</span>
                  </div>
                  <div>
                    <span>结婚状态: </span>
                    <span>{{ scope.row.employee.wedlock }}</span>
                  </div>
                  <div>
                    <span>籍贯: </span>
                    <span>{{ scope.row.employee.nativePlace }}</span>
                  </div>
                  <div>
                    <span>邮箱: </span>
                    <span>{{ scope.row.employee.email }}</span>
                  </div>
                  <div>
                    <span>手机号: </span>
                    <span>{{ scope.row.employee.phone }}</span>
                  </div>
                  <div>
                    <span>工号: </span>
                    <span>{{ scope.row.employee.workId }}</span>
                  </div>
                  <div>
                    <span>住址: </span>
                    <span>{{ scope.row.employee.address }}</span>
                  </div>
                  <div>
                    <span>聘用形式: </span>
                    <span>{{ scope.row.employee.engageForm }}</span>
                  </div>
                  <div>
                    <span>最高学历: </span>
                    <span>{{ scope.row.employee.tiptopDegree }}</span>
                  </div>
                  <div>
                    <span>专业: </span>
                    <span>{{ scope.row.employee.specialty }}</span>
                  </div>
                  <div>
                    <span>毕业学校: </span>
                    <span>{{ scope.row.employee.school }}</span>
                  </div>
                  <div>
                    <span>在职状态: </span>
                    <span>{{ scope.row.employee.workState }}</span>
                  </div>
                  <div>
                    <span>工年: </span>
                    <span>{{ scope.row.employee.workAge }}</span>
                  </div>
                </div>
                <i class='el-icon-question'></i>
              </el-tooltip>
            </template>
          </el-table-column>
          <el-table-column prop='employee.workId' label='员工号' width='100'></el-table-column>
          <el-table-column label='奖惩日期' width='200'>
            <template slot-scope='scope'>
              {{ scope.row.createDate | dateFormat }}
            </template>
          </el-table-column>
          <el-table-column prop='point' label='奖惩分数' width='200'>
            <template slot-scope='scope'>
              <el-tag v-if='scope.row.point >= 0' type='success' size='small' effect='dark'>
                +{{ scope.row.point }}
              </el-tag>
              <el-tag v-if='scope.row.point < 0' type='danger' size='small' effect='dark'>
                {{ scope.row.point }}
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column prop='reason' label='奖惩原因' width='200'>
            <template slot-scope='scope'>
              <span v-if='scope.row.point >=0'>
                {{ scope.row.reason }}
                <i class='fa fa-thumbs-up' style='color: red;'></i>
              </span>
              <span v-else>
                {{ scope.row.reason }}
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
          <el-table-column prop='employee.name' label='员工名' width='100'>
            <template slot-scope='scope'>
              <span>{{ scope.row.employee.name }} </span>
              <el-tooltip placement='right'>
                <div slot='content'>
                  <div>
                    <span>名称: </span>
                    <span>{{ scope.row.employee.name }}</span>
                  </div>
                  <div>
                    <span>性别: </span>
                    <span>{{ scope.row.employee.gender }}</span>
                  </div>
                  <div>
                    <span>生日: </span>
                    <span>{{ scope.row.employee.birthday }}</span>
                  </div>
                  <div>
                    <span>身份证号: </span>
                    <span>{{ scope.row.employee.idCard }}</span>
                  </div>
                  <div>
                    <span>结婚状态: </span>
                    <span>{{ scope.row.employee.wedlock }}</span>
                  </div>
                  <div>
                    <span>籍贯: </span>
                    <span>{{ scope.row.employee.nativePlace }}</span>
                  </div>
                  <div>
                    <span>邮箱: </span>
                    <span>{{ scope.row.employee.email }}</span>
                  </div>
                  <div>
                    <span>手机号: </span>
                    <span>{{ scope.row.employee.phone }}</span>
                  </div>
                  <div>
                    <span>工号: </span>
                    <span>{{ scope.row.employee.workId }}</span>
                  </div>
                  <div>
                    <span>住址: </span>
                    <span>{{ scope.row.employee.address }}</span>
                  </div>
                  <div>
                    <span>聘用形式: </span>
                    <span>{{ scope.row.employee.engageForm }}</span>
                  </div>
                  <div>
                    <span>最高学历: </span>
                    <span>{{ scope.row.employee.tiptopDegree }}</span>
                  </div>
                  <div>
                    <span>专业: </span>
                    <span>{{ scope.row.employee.specialty }}</span>
                  </div>
                  <div>
                    <span>毕业学校: </span>
                    <span>{{ scope.row.employee.school }}</span>
                  </div>
                  <div>
                    <span>在职状态: </span>
                    <span>{{ scope.row.employee.workState }}</span>
                  </div>
                  <div>
                    <span>工年: </span>
                    <span>{{ scope.row.employee.workAge }}</span>
                  </div>
                </div>
                <i class='el-icon-question'></i>
              </el-tooltip>
            </template>
          </el-table-column>
          <el-table-column prop='employee.workId' label='员工号' width='100'></el-table-column>
          <el-table-column label='培训日期' width='200'>
            <template slot-scope='scope'>
              {{ scope.row.createData | dateFormat }}
            </template>
          </el-table-column>
          <el-table-column prop='trainContent' label='培训内容' width='120'></el-table-column>
        </el-table>
      </el-tab-pane>
      <el-tab-pane>
        <span slot='label'><i class='el-icon-shopping-bag-1
'></i>
          考评资料</span>
        <el-table :data='employeeAppraise' style='width: 100%'>
          <el-table-column label='#' type='index' width='50'>
          </el-table-column>
          <el-table-column prop='employee.name' label='员工名' width='100'>
            <template slot-scope='scope'>
              <span>{{ scope.row.employee.name }} </span>
              <el-tooltip placement='right'>
                <div slot='content'>
                  <div>
                    <span>名称: </span>
                    <span>{{ scope.row.employee.name }}</span>
                  </div>
                  <div>
                    <span>性别: </span>
                    <span>{{ scope.row.employee.gender }}</span>
                  </div>
                  <div>
                    <span>生日: </span>
                    <span>{{ scope.row.employee.birthday }}</span>
                  </div>
                  <div>
                    <span>身份证号: </span>
                    <span>{{ scope.row.employee.idCard }}</span>
                  </div>
                  <div>
                    <span>结婚状态: </span>
                    <span>{{ scope.row.employee.wedlock }}</span>
                  </div>
                  <div>
                    <span>籍贯: </span>
                    <span>{{ scope.row.employee.nativePlace }}</span>
                  </div>
                  <div>
                    <span>邮箱: </span>
                    <span>{{ scope.row.employee.email }}</span>
                  </div>
                  <div>
                    <span>手机号: </span>
                    <span>{{ scope.row.employee.phone }}</span>
                  </div>
                  <div>
                    <span>工号: </span>
                    <span>{{ scope.row.employee.workId }}</span>
                  </div>
                  <div>
                    <span>住址: </span>
                    <span>{{ scope.row.employee.address }}</span>
                  </div>
                  <div>
                    <span>聘用形式: </span>
                    <span>{{ scope.row.employee.engageForm }}</span>
                  </div>
                  <div>
                    <span>最高学历: </span>
                    <span>{{ scope.row.employee.tiptopDegree }}</span>
                  </div>
                  <div>
                    <span>专业: </span>
                    <span>{{ scope.row.employee.specialty }}</span>
                  </div>
                  <div>
                    <span>毕业学校: </span>
                    <span>{{ scope.row.employee.school }}</span>
                  </div>
                  <div>
                    <span>在职状态: </span>
                    <span>{{ scope.row.employee.workState }}</span>
                  </div>
                  <div>
                    <span>工年: </span>
                    <span>{{ scope.row.employee.workAge }}</span>
                  </div>
                </div>
                <i class='el-icon-question'></i>
              </el-tooltip>
            </template>
          </el-table-column>
          <el-table-column prop='employee.workId' label='员工号' width='100'></el-table-column>
          <el-table-column label='考评日期' width='200'>
            <template slot-scope='scope'>
              {{ scope.row.createDate | dateFormat }}
            </template>
          </el-table-column>
          <el-table-column prop='appraiseContent' label='考评内容' width='120'>
          </el-table-column>
          <el-table-column prop='appraiseResult' label='考评结果' width='130'>
            <template slot-scope='scope'>
              <span v-if="scope.row.appraiseResult==='优秀'">
                {{ scope.row.appraiseResult }}
                <i class='el-icon-star-on' style='color: red;'></i>
              </span>
              <span v-else>
                {{ scope.row.appraiseResult }}
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
          <el-table-column prop='employee.name' label='员工名' width='100'>
            <template slot-scope='scope'>
              <span>{{ scope.row.employee.name }} </span>
              <el-tooltip placement='right'>
                <div slot='content'>
                  <div>
                    <span>名称: </span>
                    <span>{{ scope.row.employee.name }}</span>
                  </div>
                  <div>
                    <span>性别: </span>
                    <span>{{ scope.row.employee.gender }}</span>
                  </div>
                  <div>
                    <span>生日: </span>
                    <span>{{ scope.row.employee.birthday }}</span>
                  </div>
                  <div>
                    <span>身份证号: </span>
                    <span>{{ scope.row.employee.idCard }}</span>
                  </div>
                  <div>
                    <span>结婚状态: </span>
                    <span>{{ scope.row.employee.wedlock }}</span>
                  </div>
                  <div>
                    <span>籍贯: </span>
                    <span>{{ scope.row.employee.nativePlace }}</span>
                  </div>
                  <div>
                    <span>邮箱: </span>
                    <span>{{ scope.row.employee.email }}</span>
                  </div>
                  <div>
                    <span>手机号: </span>
                    <span>{{ scope.row.employee.phone }}</span>
                  </div>
                  <div>
                    <span>工号: </span>
                    <span>{{ scope.row.employee.workId }}</span>
                  </div>
                  <div>
                    <span>住址: </span>
                    <span>{{ scope.row.employee.address }}</span>
                  </div>
                  <div>
                    <span>聘用形式: </span>
                    <span>{{ scope.row.employee.engageForm }}</span>
                  </div>
                  <div>
                    <span>最高学历: </span>
                    <span>{{ scope.row.employee.tiptopDegree }}</span>
                  </div>
                  <div>
                    <span>专业: </span>
                    <span>{{ scope.row.employee.specialty }}</span>
                  </div>
                  <div>
                    <span>毕业学校: </span>
                    <span>{{ scope.row.employee.school }}</span>
                  </div>
                  <div>
                    <span>在职状态: </span>
                    <span>{{ scope.row.employee.workState }}</span>
                  </div>
                  <div>
                    <span>工年: </span>
                    <span>{{ scope.row.employee.workAge }}</span>
                  </div>
                </div>
                <i class='el-icon-question'></i>
              </el-tooltip>
            </template>
          </el-table-column>
          <el-table-column prop='employee.workId' label='员工号' width='100'></el-table-column>
          <el-table-column label='调薪日期' width='200'>
            <template slot-scope='scope'>
              {{ scope.row.createDate | dateFormat }}
            </template>
          </el-table-column>
          <el-table-column label='薪资' width='120'>
            <template slot-scope='scope'>
              {{ scope.row.salary }}
              <span v-if='scope.row.salary >= 0'>
                <i class='fa fa-arrow-circle-up' style='color: green;'></i>
              </span>
              <span v-else>
                <i class='fa fa-arrow-circle-down' style='color: red;'></i>
              </span>
            </template>
          </el-table-column>
          <el-table-column prop='reason' label='调薪原因' width='300'>
          </el-table-column>
        </el-table>
      </el-tab-pane>
      <el-tab-pane>
        <span slot='label'><i class='el-icon-chat-square'></i> 调动资料</span>
        <el-table :data='employeeMove' style='width: 100%'>
          <el-table-column label='#' type='index' width='50'>
          </el-table-column>
          <el-table-column prop='employee.name' label='员工名' width='100'>
            <template slot-scope='scope'>
              <span>{{ scope.row.employee.name }} </span>
              <el-tooltip placement='right'>
                <div slot='content'>
                  <div>
                    <span>名称: </span>
                    <span>{{ scope.row.employee.name }}</span>
                  </div>
                  <div>
                    <span>性别: </span>
                    <span>{{ scope.row.employee.gender }}</span>
                  </div>
                  <div>
                    <span>生日: </span>
                    <span>{{ scope.row.employee.birthday }}</span>
                  </div>
                  <div>
                    <span>身份证号: </span>
                    <span>{{ scope.row.employee.idCard }}</span>
                  </div>
                  <div>
                    <span>结婚状态: </span>
                    <span>{{ scope.row.employee.wedlock }}</span>
                  </div>
                  <div>
                    <span>籍贯: </span>
                    <span>{{ scope.row.employee.nativePlace }}</span>
                  </div>
                  <div>
                    <span>邮箱: </span>
                    <span>{{ scope.row.employee.email }}</span>
                  </div>
                  <div>
                    <span>手机号: </span>
                    <span>{{ scope.row.employee.phone }}</span>
                  </div>
                  <div>
                    <span>工号: </span>
                    <span>{{ scope.row.employee.workId }}</span>
                  </div>
                  <div>
                    <span>住址: </span>
                    <span>{{ scope.row.employee.address }}</span>
                  </div>
                  <div>
                    <span>聘用形式: </span>
                    <span>{{ scope.row.employee.engageForm }}</span>
                  </div>
                  <div>
                    <span>最高学历: </span>
                    <span>{{ scope.row.employee.tiptopDegree }}</span>
                  </div>
                  <div>
                    <span>专业: </span>
                    <span>{{ scope.row.employee.specialty }}</span>
                  </div>
                  <div>
                    <span>毕业学校: </span>
                    <span>{{ scope.row.employee.school }}</span>
                  </div>
                  <div>
                    <span>在职状态: </span>
                    <span>{{ scope.row.employee.workState }}</span>
                  </div>
                  <div>
                    <span>工年: </span>
                    <span>{{ scope.row.employee.workAge }}</span>
                  </div>
                </div>
                <i class='el-icon-question'></i>
              </el-tooltip>
            </template>
          </el-table-column>
          <el-table-column prop='employee.workId' label='员工号' width='100'></el-table-column>
          <el-table-column label='调动日期' width='200'>
            <template slot-scope='scope'>
              {{ scope.row.removeDate | dateFormat }}
            </template>
          </el-table-column>
          <el-table-column prop='beforeDepartmentName' label='调前部门' width='120'>
          </el-table-column>
          <el-table-column prop='beforeJobName' label='调前职位' width='120'>
          </el-table-column>
          <el-table-column prop='reason' label='调薪原因' width='180'>
          </el-table-column>
          <el-table-column prop='afterDepartmentName' label='调后部门' width='120'>
          </el-table-column>
          <el-table-column prop='afterJobName' label='调后职位' width='120'>
          </el-table-column>
          <el-table-column prop='remark' label='备注' width='120'>
          </el-table-column>
        </el-table>
      </el-tab-pane>
      <el-tab-pane>
        <span slot='label'><i class='el-icon-chat-dot-round'></i> 工资信息</span>
        <el-table :data='empSalaryInfo' stripe>
          <el-table-column label='#' type='index' width='50'></el-table-column>
          <el-table-column prop='employeeName' label='员工名' width='100'></el-table-column>
          <el-table-column prop='workId' label='员工号' width='100'></el-table-column>
          <el-table-column prop='departmentName' label='部门' width='100'></el-table-column>
          <el-table-column prop='jobLevelName' label='职称' width='100'></el-table-column>
          <el-table-column prop='salary.name' label='账套名称' width='120'></el-table-column>
          <el-table-column prop='wagesPayable' label='工资' width='120'>
            <template slot-scope='scope'>
              <span style='display: flex;justify-content: right;align-items: center'>
                <span>{{ scope.row.wagesPayable }}</span>
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
                <i class='el-icon-question' style='margin-left: 10px;' />
            </el-tooltip>
              </span>
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>
      <el-tab-pane>
        <span slot='label'><i class='el-icon-guide'></i> 其他信息</span>
        其他信息
      </el-tab-pane>
      <el-pagination
        @size-change='handleSizeChange'
        class='el_page_div'
        @current-change='handleNumChange'
        :page-sizes='[5, 8, 10]'
        :page-size='pageSize'
        layout='sizes, prev, pager, next'
        :total='pageTotal'>
      </el-pagination>
    </el-tabs>
  </div>
</template>

<script>
export default {
  data() {
    return {
      pageSize: 5,
      pageNum: 1,
      pageTotal: 0,
      employeeAdv: [],
      employeeTrain: [],
      employeeAppraise: [],
      employeeAdjustSalary: [],
      employeeMove: [],
      empSalaryInfo: [],
      currentTableIndex: { index: '0' }
    }
  },
  methods: {
    handleNumChange(val) {
      this.pageNum = val
      this.tabChange(this.currentTableIndex, true)
    },
    handleSizeChange(val) {
      this.pageSize = val
      this.tabChange(this.currentTableIndex, true)
    },
    tabChange(tab, isCurrentChange) {
      this.currentTableIndex = tab
      if (!isCurrentChange) {
        this.pageSize = 5
        this.pageNum = 1
      }
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
      this.getRequest('/emp/adv/c/' + this.pageNum + '/' + this.pageSize).then(res => {
        this.employeeAdv = res.data.obj.list
        this.pageTotal = res.data.obj.total
      })
    },
    showEmployeeTrain() {
      this.getRequest('/emp/adv/train/' + this.pageNum + '/' + this.pageSize).then(res => {
        this.employeeTrain = res.data.obj.list
        this.pageTotal = res.data.obj.total
      })
    },
    showEmployeeAppraise() {
      this.getRequest('/emp/adv/app/' + this.pageNum + '/' + this.pageSize).then(res => {
        this.employeeAppraise = res.data.obj.list
        this.pageTotal = res.data.obj.total
      })
    },
    showEmployeeAdjustSalary() {
      this.getRequest('/emp/adv/adj/' + this.pageNum + '/' + this.pageSize).then(res => {
        this.employeeAdjustSalary = res.data.obj.list
        this.pageTotal = res.data.obj.total
      })
    },
    showEmployeeMove() {
      this.getRequest('/emp/adv/remove/' + this.pageNum + '/' + this.pageSize).then(res => {
        this.employeeMove = res.data.obj.list
        this.pageTotal = res.data.obj.total
      })
    },
    showSalaryMonth() {
      this.getRequest('/emp/adv/salaryInfo/' + this.pageNum + '/' + this.pageSize).then(res => {
        this.empSalaryInfo = res.data.obj.list
        this.pageTotal = res.data.obj.total
      })
    }
  },
  created() {
    this.init()
  },
  filters: {
    DateFilterMonth: function(time) {
      const t = new Date(time)
      return t.getFullYear() + '/' + (t.getMonth() + 1)
    }
  }
}
</script>

<style lang='less'></style>
