<template>
  <div>
    <el-breadcrumb separator='/' style='margin-bottom: 15px;'>
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>工资账套管理</el-breadcrumb-item>
    </el-breadcrumb>
    <el-button icon='el-icon-circle-plus-outline' @click='addSalaryChangeStatus'
    >添加工资账单
    </el-button
    >
    <el-button icon='el-icon-refresh' @click='flushTab'></el-button>
    <el-card class='box-card' style='margin-top: 15px;'>
      <el-table
        :data='tableData'
        stripe
        style='width: 100%'
        border
        @selection-change='handleSelectionChange'
      >
        <el-table-column type='selection' width='55'></el-table-column>
        <el-table-column prop='name' label='账单名称' width='150'></el-table-column>
        <el-table-column prop='basicSalary' label='基本工资' width='90'></el-table-column>
        <el-table-column prop='trafficSalary' label='交通补助' width='90'></el-table-column>
        <el-table-column prop='lunchSalary' label='午餐补助' width='90'></el-table-column>
        <el-table-column label='奖金' width='50' prop='bonus'></el-table-column>
        <el-table-column prop='createDate' label='启用时间' width='200'>
          <template slot-scope='scope'>
            {{ scope.row.createDate | dateFormat }}
          </template>
        </el-table-column>
        <el-table-column label='养老金'>
          <el-table-column prop='pensionPer' label='比率'></el-table-column>
          <el-table-column prop='pensionBase' label='基数'></el-table-column>
        </el-table-column>
        <el-table-column label='医疗保险'>
          <el-table-column prop='medicalPer' label='比率'></el-table-column>
          <el-table-column prop='medicalBase' label='基数'></el-table-column>
        </el-table-column>
        <el-table-column label='公积金'>
          <el-table-column prop='accumulationFundPer' label='比率'></el-table-column>
          <el-table-column prop='accumulationFundBase' label='基数'></el-table-column>
        </el-table-column>
        <el-table-column label='操作' width='180' fixed='right'>
          <template slot-scope='scope'>
            <el-button
              icon='el-icon-edit'
              type='info'
              size='mini'
              @click='editSal(scope.row)'
            >编辑
            </el-button>
            <el-button
              icon='el-icon-delete'
              type='danger'
              size='mini'
              @click='deleteSal(scope.row.id)'
            >删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-button
        type='warning'
        size='default'
        @click='deleteMany'
        style='margin-top: 15px;'
      >批量删除
      </el-button
      >
    </el-card>
    <el-dialog
      :title='isEditOrAdd'
      :visible.sync='centerDialogVisible'
      width='50%'
      center
    >
      <el-form :model='salaryDate'
               :rules='addSalaryRoles'
               ref='salaryDateForm'>
        <el-tabs tab-position='left' v-model='activeName'>
          <el-tab-pane label='基本工资' name='0'>
            <div style='margin-left: 50%;transform: translateX(-50%);'>
              基本工资：
              <el-form-item prop='basicSalary'>
                <el-input
                  v-model='salaryDate.basicSalary'
                  placeholder='请输入基本工资'
                  style='width: 200px;'
                ></el-input>
              </el-form-item>
            </div>
          </el-tab-pane>
          <el-tab-pane label='交通补助' name='1'>
            <div style='margin-left: 50%;transform: translateX(-50%);'>
              交通补助：
              <el-form-item prop='trafficSalary'>
                <el-input
                  v-model='salaryDate.trafficSalary'
                  placeholder='请输入交通补助'
                  style='width: 200px;'
                ></el-input>
              </el-form-item>
            </div>
          </el-tab-pane>
          <el-tab-pane label='午餐补助' name='2'>
            <div style='margin-left: 50%;transform: translateX(-50%);'>
              午餐补助：
              <el-form-item prop='lunchSalary'>
                <el-input
                  v-model='salaryDate.lunchSalary'
                  placeholder='请输入午餐补助'
                  style='width: 200px;'
                ></el-input>
              </el-form-item>
            </div>
          </el-tab-pane>
          <el-tab-pane label='奖金' name='3'>
            <div style='margin-left: 50%;transform: translateX(-50%);'>
              奖金：
              <el-form-item prop='bonus'>
                <el-input
                  v-model='salaryDate.bonus'
                  placeholder='请输入奖金'
                  style='width: 200px;'
                ></el-input>
              </el-form-item>
            </div>
          </el-tab-pane>
          <el-tab-pane label='养老金' name='4'>
            <div style='margin-left: 50%;transform: translateX(-50%);'>
              比率：
              <el-form-item prop='pensionPer'>
                <el-input
                  v-model='salaryDate.pensionPer'
                  placeholder='请输入养老金比率'
                  style='width: 200px;'
                ></el-input>
              </el-form-item>
            </div>
            <div
              style='margin-left: 50%;transform: translateX(-50%);margin-top: 15px;'
            >
              基数：
              <el-form-item prop='pensionBase'>
                <el-input
                  v-model='salaryDate.pensionBase'
                  placeholder='请输入养老基数'
                  style='width: 200px;'
                ></el-input>
              </el-form-item>
            </div>
          </el-tab-pane>
          <el-tab-pane label='医疗保险' name='5'>
            <div style='margin-left: 50%;transform: translateX(-50%);'>
              比率：
              <el-form-item prop='medicalPer'>
                <el-input
                  v-model='salaryDate.medicalPer'
                  placeholder='请输入医疗保险比率'
                  style='width: 200px;'
                ></el-input>
              </el-form-item>
            </div>
            <div
              style='margin-left: 50%;transform: translateX(-50%);margin-top: 15px;'
            >
              基数：
              <el-form-item prop='medicalBase'>
                <el-input
                  v-model='salaryDate.medicalBase'
                  placeholder='请输入医疗保险基数'
                  style='width: 200px;'
                ></el-input>
              </el-form-item>
            </div>
          </el-tab-pane>
          <el-tab-pane label='公积金' name='6'>
            <div style='margin-left: 50%;transform: translateX(-50%);'>
              比率：
              <el-form-item prop='accumulationFundPer'>
                <el-input
                  v-model='salaryDate.accumulationFundPer'
                  placeholder='请输入公积金比率'
                  style='width: 200px;'
                ></el-input>
              </el-form-item>
            </div>
            <div
              style='margin-left: 50%;transform: translateX(-50%);margin-top: 15px;'
            >
              基数：
              <el-form-item prop='accumulationFundBase'>
                <el-input
                  v-model='salaryDate.accumulationFundBase'
                  placeholder='请输入公积金基数'
                  style='width: 200px;'
                ></el-input>
              </el-form-item>
            </div>
          </el-tab-pane>
          <el-tab-pane label='启动时间' name='7'>
            <div
              style='margin-left: 50%;transform: translateX(-50%);margin-top: 15px;'
            >
              启动时间：
              <el-form-item prop='createDate'>
                <el-date-picker
                  v-model='salaryDate.createDate'
                  align='right'
                  type='date'
                  style='width: 200px;'
                  placeholder='选择日期'
                  value-format='yyyy-MM-dd HH:mm:ss'
                  :picker-options='pickerOptions'
                >
                </el-date-picker>
              </el-form-item>
            </div>
          </el-tab-pane>
          <el-tab-pane label='账单名称' name='8'>
            <div
              style='margin-left: 50%;transform: translateX(-50%);margin-top: 15px;'
            >
              账单名称：
              <el-form-item prop='name'>
                <el-input
                  v-model='salaryDate.name'
                  placeholder='请输入账单名称'
                  style='width: 200px;'
                ></el-input>
              </el-form-item>
            </div>
          </el-tab-pane>
        </el-tabs>
      </el-form>
      <span slot='footer' class='dialog-footer'>
      <el-button @click='up' v-if='activeName < 8'>上一步</el-button>
      <el-button type='primary' @click='down' v-if='activeName < 8'
      >下一步</el-button
      >
      <el-button
        @click='centerDialogVisible = false'
        v-if="activeName === '8'">取 消</el-button
      >
      <el-button type='primary' v-if="activeName === '8'" @click='addSalary'>完成</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    const intRegExp = new RegExp(/^[1-9]\d*$/)
    const floatRegExp = new RegExp(/^[0-9]\d*(\.\d+)?$/)
    const intRegExpTest = (rule, value, callback) => {
      if (!intRegExp.test(value)) {
        callback(new Error('未填写或格式错误!'))
      } else {
        callback()
      }
    }
    const floatRegExpTest = (rule, value, callback) => {
      if (!floatRegExp.test(value)) {
        callback(new Error('未填写或格式错误!'))
      } else {
        callback()
      }
    }
    return {
      manyDelId: [],
      salaryDate: {
        accumulationFundBase: 0,
        accumulationFundPer: 0,
        basicSalary: 0,
        bonus: 0,
        createDate: '',
        lunchSalary: 0,
        medicalBase: 0,
        medicalPer: 0,
        pensionBase: 0,
        pensionPer: 0,
        trafficSalary: 0,
        name: ''
      },
      addSalaryRoles: {
        accumulationFundBase: [
          { validator: intRegExpTest }
        ],
        accumulationFundPer: [
          { validator: floatRegExpTest }
        ],
        basicSalary: [
          { validator: intRegExpTest }
        ],
        bonus: [
          { validator: intRegExpTest }
        ],
        createDate: [
          { required: true, message: '请选择时间' }
        ],
        lunchSalary: [
          { validator: intRegExpTest }
        ],
        medicalBase: [
          { validator: intRegExpTest }
        ],
        medicalPer: [
          { validator: floatRegExpTest }
        ],
        pensionBase: [
          { validator: intRegExpTest }
        ],
        pensionPer: [
          { validator: floatRegExpTest }
        ],
        trafficSalary: [
          { validator: intRegExpTest }
        ],
        name: [
          { required: true, message: '请填写名称' }
        ]
      },
      isEditOrAdd: '添加账套',
      tableData: [],
      centerDialogVisible: false,
      pickerOptions: {
        disabledDate(time) {
          return time.getTime() > Date.now()
        },
        shortcuts: [
          {
            text: '今天',
            onClick(picker) {
              picker.$emit('pick', new Date())
            }
          },
          {
            text: '昨天',
            onClick(picker) {
              const date = new Date()
              date.setTime(date.getTime() - 3600 * 1000 * 24)
              picker.$emit('pick', date)
            }
          },
          {
            text: '一周前',
            onClick(picker) {
              const date = new Date()
              date.setTime(date.getTime() - 3600 * 1000 * 24 * 7)
              picker.$emit('pick', date)
            }
          }
        ]
      },
      activeName: '0'
    }
  },
  methods: {
    deleteMany() {
      if (this.manyDelId.length < 1) {
        return this.$message.info('请勾选需要删除的账套！')
      }
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          let ids = '?'
          this.manyDelId.forEach(e => {
            ids += 'ids=' + e.id + '&'
          })
          this.deleteRequest('/sal/sob/delete/many/' + ids).then(res => {
            this.$message.success(res.data.msg)
            this.init()
          })
        })
        .catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
    },
    handleSelectionChange(val) {
      this.manyDelId = val
    },
    editSal(item) {
      this.refiledData()
      this.salaryDate = item
      this.salaryDate.allsalary = ''
      this.isEditOrAdd = '修改账套'
      this.centerDialogVisible = true
    },
    deleteSal(id) {
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          this.deleteRequest('/sal/sob/delete/' + id).then(resp => {
            this.$message.success(resp.data.msg)
            this.init()
          })
        })
        .catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
    },
    flushTab() {
      this.init()
      this.$message.success('已刷新')
    },
    refiledData() {
      this.salaryDate.accumulationFundBase = 0
      this.salaryDate.accumulationFundPer = 0
      this.salaryDate.basicSalary = 0
      this.salaryDate.bonus = 0
      this.salaryDate.createDate = null
      this.salaryDate.lunchSalary = 0
      this.salaryDate.medicalBase = 0
      this.salaryDate.medicalPer = 0
      this.salaryDate.pensionBase = 0
      this.salaryDate.pensionPer = 0
      this.salaryDate.trafficSalary = 0
      this.salaryDate.name = null
      this.centerDialogVisible = false
      this.activeName = '0'
    },
    addSalary() {
      this.$refs['salaryDateForm'].validate((valid) => {
        if (valid) {
          if (this.isEditOrAdd === '添加账套') {
            this.putRequest('/sal/sob/add', this.salaryDate).then(resp => {
              this.$message.success(resp.data.msg)
              if (resp.data.status === 200) {
                this.centerDialogVisible = false
                this.init()
              }
            })
          } else {
            this.putRequest('/sal/sob/modify', this.salaryDate).then(resp => {
              this.$message.success(resp.data.msg)
              if (resp.data.status === 200) {
                this.centerDialogVisible = false
                this.init()
              }
            })
          }
        } else {
          this.$message.warning('有表单未填写!')
          return false
        }
      })
    },
    up() {
      if (Number(this.activeName) == 0) return
      this.activeName = Number(this.activeName) - 1 + ''
    },
    down() {
      if (Number(this.activeName) >= 8) {
        return
      }
      this.activeName = Number(this.activeName) + 1 + ''
    },
    init() {
      this.getRequest('/sal/sob/').then(resp => {
        this.tableData = resp.data.obj
      })
    },
    addSalaryChangeStatus() {
      this.refiledData()
      this.isEditOrAdd = '添加账套'
      this.centerDialogVisible = true
    }
  },
  created() {
    this.init()
  }
}
</script>

<style lang='less' scope></style>
