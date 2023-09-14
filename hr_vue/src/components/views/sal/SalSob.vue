<template>
  <div>
    <el-breadcrumb separator="/" style="margin-bottom: 15px;">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>工资账套管理</el-breadcrumb-item>
    </el-breadcrumb>
    <el-button icon="el-icon-circle-plus-outline" @click="addSalay"
      >添加工资账单</el-button
    >
    <el-button icon="el-icon-refresh" @click="flushTab"></el-button>
    <el-card class="box-card" style="margin-top: 15px;">
      <el-table
        :data="tableData"
        stripe
        style="width: 100%"
        border
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="55"> </el-table-column>
        <el-table-column prop="name" label="账单名称" width="150">
        </el-table-column>
        <el-table-column prop="basicsalary" label="基本工资" width="90">
        </el-table-column>
        <el-table-column prop="trafficsalary" label="交通补助" width="90">
        </el-table-column>
        <el-table-column prop="lunchsalary" label="午餐补助" width="90">
        </el-table-column>
        <el-table-column label="奖金" width="50" prop="bonus">
        </el-table-column>
        <el-table-column prop="createdate" label="启用时间" width="150">
          <template slot-scope="scope">
            {{ scope.row.createdate | dateFormat }}
          </template>
        </el-table-column>
        <el-table-column label="养老金">
          <el-table-column prop="pensionper" label="比率"> </el-table-column>
          <el-table-column prop="pensionbase" label="基数"> </el-table-column>
        </el-table-column>
        <el-table-column label="医疗保险">
          <el-table-column prop="medicalper" label="比率"> </el-table-column>
          <el-table-column prop="medicalbase" label="基数"> </el-table-column>
        </el-table-column>
        <el-table-column label="公积金">
          <el-table-column prop="accumulationfundper" label="比率">
          </el-table-column>
          <el-table-column prop="accumulationfundbase" label="基数">
          </el-table-column>
        </el-table-column>
        <el-table-column label="操作" width="180" fixed="right">
          <template slot-scope="scope">
            <el-button
              icon="el-icon-edit"
              type="info"
              size="mini"
              @click="editSal(scope.row)"
            ></el-button>
            <el-button
              icon="el-icon-delete"
              type="danger"
              size="mini"
              @click="deleteSal(scope.row.id)"
            ></el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-button
        type="warning"
        size="default"
        @click="deletMany"
        style="margin-top: 15px;"
        >批量删除</el-button
      >
    </el-card>

    <el-dialog
      :title="isEditOrAdd"
      :visible.sync="centerDialogVisible"
      width="50%"
      center
      @closed="closeDia"
    >
      <el-tabs tab-position="left" @tab-click="tabclick" v-model="activeName">
        <el-tab-pane label="基本工资" name="0">
          <div style="margin-left: 50%;transform: translateX(-50%);">
            基本工资：<el-input
              v-model="salaryDate.basicsalary"
              placeholder="请输入基本工资"
              style="width: 200px;"
            ></el-input>
          </div>
        </el-tab-pane>
        <el-tab-pane label="交通补助" name="1">
          <div style="margin-left: 50%;transform: translateX(-50%);">
            交通补助：<el-input
              v-model="salaryDate.trafficsalary"
              placeholder="请输入交通补助"
              style="width: 200px;"
            ></el-input>
          </div>
        </el-tab-pane>
        <el-tab-pane label="午餐补助" name="2">
          <div style="margin-left: 50%;transform: translateX(-50%);">
            午餐补助：<el-input
              v-model="salaryDate.lunchsalary"
              placeholder="请输入午餐补助"
              style="width: 200px;"
            ></el-input>
          </div>
        </el-tab-pane>
        <el-tab-pane label="奖金" name="3">
          <div style="margin-left: 50%;transform: translateX(-50%);">
            奖金：<el-input
              v-model="salaryDate.bonus"
              placeholder="请输入奖金"
              style="width: 200px;"
            ></el-input>
          </div>
        </el-tab-pane>
        <el-tab-pane label="养老金" name="4">
          <div style="margin-left: 50%;transform: translateX(-50%);">
            比率：<el-input
              v-model="salaryDate.pensionper"
              placeholder="请输入养老金比率"
              style="width: 200px;"
            ></el-input>
          </div>
          <div
            style="margin-left: 50%;transform: translateX(-50%);margin-top: 15px;"
          >
            基数：<el-input
              v-model="salaryDate.pensionbase"
              placeholder="请输入养老基数"
              style="width: 200px;"
            ></el-input>
          </div>
        </el-tab-pane>
        <el-tab-pane label="医疗保险" name="5">
          <div style="margin-left: 50%;transform: translateX(-50%);">
            比率：<el-input
              v-model="salaryDate.medicalper"
              placeholder="请输入医疗保险比率"
              style="width: 200px;"
            ></el-input>
          </div>
          <div
            style="margin-left: 50%;transform: translateX(-50%);margin-top: 15px;"
          >
            基数：<el-input
              v-model="salaryDate.medicalbase"
              placeholder="请输入医疗保险基数"
              style="width: 200px;"
            ></el-input>
          </div>
        </el-tab-pane>
        <el-tab-pane label="公积金" name="6">
          <div style="margin-left: 50%;transform: translateX(-50%);">
            比率：<el-input
              v-model="salaryDate.accumulationfundper"
              placeholder="请输入公积金比率"
              style="width: 200px;"
            ></el-input>
          </div>
          <div
            style="margin-left: 50%;transform: translateX(-50%);margin-top: 15px;"
          >
            基数：<el-input
              v-model="salaryDate.accumulationfundbase"
              placeholder="请输入公积金基数"
              style="width: 200px;"
            ></el-input>
          </div>
        </el-tab-pane>
        <el-tab-pane label="启动时间" name="7">
          <div
            style="margin-left: 50%;transform: translateX(-50%);margin-top: 15px;"
          >
            启动时间：<el-date-picker
              v-model="salaryDate.createdate"
              align="right"
              type="date"
              style="width: 200px;"
              placeholder="选择日期"
              :picker-options="pickerOptions"
            >
            </el-date-picker>
          </div>
        </el-tab-pane>
        <el-tab-pane label="账单名称" name="8">
          <div
            style="margin-left: 50%;transform: translateX(-50%);margin-top: 15px;"
          >
            账单名称：<el-input
              v-model="salaryDate.name"
              placeholder="请输入账单名称"
              style="width: 200px;"
            ></el-input>
          </div>
        </el-tab-pane>
      </el-tabs>
      <span slot="footer" class="dialog-footer">
        <el-button @click="up" v-if="activeName < 8">上一步</el-button>
        <el-button type="primary" @click="down" v-if="activeName < 8"
          >下一步</el-button
        >
        <el-button
          @click="centerDialogVisible = false"
          v-if="activeName === '8'"
          >取 消</el-button
        >
        <el-button type="primary" v-if="activeName === '8'" @click="addSalary"
          >完成</el-button
        >
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      manyDelId: [],
      salaryDate: {
        accumulationfundbase: 0,
        accumulationfundper: 0,
        basicsalary: 0,
        bonus: 0,
        createdate: '',
        lunchsalary: 0,
        medicalbase: 0,
        medicalper: 0,
        pensionbase: 0,
        pensionper: 0,
        trafficsalary: 0,
        name: ''
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
    deletMany() {
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
      console.log(item)
      this.centerDialogVisible = true
      for (const i in item) {
        this.salaryDate[i] = item[i]
      }
      this.salaryDate.createdate = new Date(this.salaryDate.createdate)
      this.salaryDate.allsalary = ''
      this.isEditOrAdd = '修改账套'
    },
    deleteSal(id) {
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          this.deleteRequest('/sal/sob/' + id).then(resp => {
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
    },
    refiledData() {
      this.salaryDate.accumulationfundbase = 0
      this.salaryDate.accumulationfundper = 0
      this.salaryDate.basicsalary = 0
      this.salaryDate.bonus = 0
      this.salaryDate.createdate = ''
      this.salaryDate.lunchsalary = 0
      this.salaryDate.medicalbase = 0
      this.salaryDate.medicalper = 0
      this.salaryDate.pensionbase = 0
      this.salaryDate.pensionper = 0
      this.salaryDate.trafficsalary = 0
      this.salaryDate.name = ''
      this.centerDialogVisible = false
      this.activeName = '0'
    },
    closeDia() {
      this.refiledData()
    },
    addSalary() {
      if (this.salaryDate.name === '' || this.salaryDate.createdate === '') {
        return this.$message.info('请填写必要项！')
      }
      if (this.isEditOrAdd === '添加账套') {
        this.putRequest('/sal/sob/', this.salaryDate).then(resp => {
          this.$message.success(resp.data.msg)
          this.refiledData()
        })
      } else {
        this.putRequest('/sal/sob/change/one', this.salaryDate).then(resp => {
          this.$message.success(resp.data.msg)
          this.refiledData()
        })
      }
    },
    up() {
      if (this.activeName === 0) {
        return
      }
      console.log(this.activeName)
      this.activeName = this.activeName - 1 + ''
    },
    down() {
      if (this.activeName >= 8) {
        return
      }
      console.log(this.activeName)
      this.activeName = Number(this.activeName) + 1 + ''
    },
    init() {
      this.getRequest('/sal/sob/').then(resp => {
        this.tableData = resp.data.obj
      })
    },
    addSalay() {
      this.isEditOrAdd = '添加账套'
      this.centerDialogVisible = true
    }
  },
  created() {
    this.init()
  }
}
</script>

<style lang="less" scope></style>
