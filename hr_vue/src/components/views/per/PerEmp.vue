<template>
  <div>
    <el-breadcrumb separator='/' style='margin-bottom:15px;'>
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>员工资料</el-breadcrumb-item>
    </el-breadcrumb>
    <el-input
      placeholder='请输入员工名称'
      v-model='searchData'
      style='width: 500px;'
    >
      <el-button
        slot='append'
        icon='el-icon-search'
        @click='searchBtn'
      ></el-button>
    </el-input>
    <!--  -->
    <el-card
      shadow='hover'
      :body-style="{ padding: '20px' }"
      style='margin-top: 15px;width: 1100px;'
    >
      <div slot='header' style='text-align: center;font-weight: 500;'>
        <span>查询出的员工</span>
      </div>
      <!-- card body -->
      <el-table :data='tableData' stripe style='width: 100%'>
        <el-table-column label='#' type='index' width='50'></el-table-column>
        <el-table-column prop='name' label='姓名' width='100'>
        </el-table-column>
        <el-table-column prop='workid' label='工号' width='90'>
        </el-table-column>
        <el-table-column prop='idcard' label='身份证号码' width='180'>
        </el-table-column>
        <el-table-column prop='position.name' label='所在部门' width='180'>
        </el-table-column>
        <el-table-column prop='joblevel.name' label='职位' width='180'>
        </el-table-column>
        <el-table-column label='操作' width='180'>
          <template slot-scope='scope'>
            <el-button
              type='info'
              size='mini'
              @click='changeInfo(scope.row)'
            >修改
            </el-button
            >
            <el-button
              type='danger'
              size='mini'
              @click='delEmp(scope.row.id)'
            >删除
            </el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <el-dialog title='修改用户名称' :visible.sync='dialogVisible' width='30%'>
      名称:
      <el-input v-model='Emp.name' size='normal'></el-input>
      <span slot='footer' class='dialog-footer'>
        <el-button @click='dialogVisible = false'>取 消</el-button>
        <el-button type='primary' @click='changeEmpName'>确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      searchData: '',
      tableData: [],
      dialogVisible: false,
      Emp: {
        id: '',
        name: ''
      }
    }
  },
  methods: {
    searchBtn() {
      if (!this.searchData || this.searchData.trim() == '')
        return this.$message.error('请输入！！')
      this.getRequest('/per/emp/byEmpName/' + this.searchData).then(res => {
        this.tableData = res.data.obj
        if (res.data.obj.length > 0) {
          this.$message.success('查询成功')
        } else {
          this.$message.warning('未找到!')
        }
      })
    },
    changeInfo(employee) {
      this.dialogVisible = true
      this.Emp.id = employee.id
      this.Emp.name = employee.name
    },
    delEmp(id) {
      this.deleteRequest('/per/emp/delete/' + id).then(res => {
        this.$message.success(res.data.msg)
        this.tableData = []
      })
    },
    changeEmpName() {
      this.putRequest('/per/emp/modify', this.Emp).then(res => {
        this.$message.success(res.data.msg)
        this.tableData = []
      })
      this.dialogVisible = false
    }
  }
}
</script>

<style lang='less' scoped></style>
