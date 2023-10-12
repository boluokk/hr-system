<template>
  <div>
    <el-card shadow='always' :body-style="{ padding: '20px' }">
      <div slot='header'></div>
      <el-table :data='tableData' style='width: 100%'>
        <el-table-column prop='employeeName' label='员工名称' width='90'>
        </el-table-column>
        <el-table-column prop='reason' label='原因' width='400'>
        </el-table-column>
        <el-table-column label='时间' width='180'>
          <template slot-scope='scope'>
            {{ scope.row.removedate | dateFormat }}
          </template>
        </el-table-column>
        <el-table-column prop='oldDepartName' label='旧部门' width='90'></el-table-column>
        <el-table-column prop='oldJoblevelName' label='旧职位' width='90'></el-table-column>
        <el-table-column prop='newDepartName' label='新部门' width='90'></el-table-column>
        <el-table-column prop='newJoblevelName' label='新职位' width='90'></el-table-column>
        <el-table-column prop='remark' label='备注' width='350'></el-table-column>
        <el-table-column label='操作' width='200'>
          <template slot-scope='scope'>
            <el-button
              type='info'
              icon='el-icon-edit'
              @click='showEdit(scope.row)'
              size='mini'
              style='margin-right: 10px;'
            >编辑
            </el-button>
            <el-popconfirm
              confirm-button-text='好的'
              cancel-button-text='不用了'
              icon='el-icon-info'
              icon-color='red'
              title='这是一段内容确定删除吗？'
              @confirm='del(scope.row.id)'
            >
              <el-button
                type='danger'
                icon='el-icon-delete'
                slot='reference'
                size='mini'
              >删除
              </el-button>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        class='el_page_div'
        @size-change='handleSizeChange'
        @current-change='handleCurrentChange'
        :page-sizes='[5, 8, 10]'
        :page-size='pageSize'
        layout='sizes, prev, pager, next'
        :total='pageTotal'>
      </el-pagination>
      <el-button
        type='primary'
        @click='addDialogVisible = true'
        style='margin-top: 15px;'
      >添加
      </el-button
      >
      <el-dialog
        title='修改'
        :visible.sync='editDialogVisible'
        width='30%'
      >
        <el-form ref='editEmpSalaryFormRef' :model='editEmpMvForm' label-width='80px' label-position='left'>
          <el-form-item label='原因'>
            <el-input v-model='editEmpMvForm.reason'></el-input>
          </el-form-item>
          <el-form-item label='时间'>
            <el-date-picker type='date' placeholder='选择日期' v-model='editEmpMvForm.removedate'
                            value-format='yyyy-MM-dd HH:mm:ss'
                            style='width: 100%;'></el-date-picker>
          </el-form-item>
          <el-form-item label='旧部门'>
            <el-input v-model='editEmpMvForm.oldDepartName'></el-input>
          </el-form-item>
          <el-form-item label='旧职位'>
            <el-input v-model='editEmpMvForm.oldJoblevelName'></el-input>
          </el-form-item>
          <el-form-item label='新部门'>
            <el-input v-model='editEmpMvForm.newDepartName'></el-input>
          </el-form-item>
          <el-form-item label='新职位'>
            <el-input v-model='editEmpMvForm.newJoblevelName'></el-input>
          </el-form-item>
          <el-form-item label='备注'>
            <el-input v-model='editEmpMvForm.remark' type='textarea'></el-input>
          </el-form-item>
        </el-form>
        <span slot='footer' class='dialog-footer'>
          <el-button @click='editDialogVisible = false'>取 消</el-button>
          <el-button type='warning' @click='update()'
          >确 定</el-button
          >
        </span>
      </el-dialog>
      <el-dialog
        title='添加'
        :visible.sync='addDialogVisible'
        width='30%'
      >
        <el-form ref='addEmpSalaryFormRef' :model='addEmpMvForm' label-width='80px' label-position='left'>
          <el-form-item label='员工号'>
            <el-input v-model='workId' maxlength='8' show-word-limit></el-input>
          </el-form-item>
          <el-form-item label='原因'>
            <el-input v-model='addEmpMvForm.reason'></el-input>
          </el-form-item>
          <el-form-item label='时间'>
            <el-date-picker type='date' placeholder='选择日期' v-model='addEmpMvForm.asdate'
                            value-format='yyyy-MM-dd HH:mm:ss'
                            style='width: 100%;'></el-date-picker>
          </el-form-item>
          <el-form-item label='新部门'>
            <el-input v-model='addEmpMvForm.newDepartName'></el-input>
          </el-form-item>
          <el-form-item label='新职位'>
            <el-input v-model='addEmpMvForm.newJoblevelName'></el-input>
          </el-form-item>
          <el-form-item label='备注'>
            <el-input v-model='addEmpMvForm.remark' type='textarea'></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type='primary' @click='add'>新建</el-button>
            <el-button @click='addDialogVisible = false'>取消</el-button>
          </el-form-item>
        </el-form>
      </el-dialog>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      tableData: [],
      pageNum: 1,
      pageSize: 5,
      pageTotal: 0,
      addDialogVisible: false,
      editDialogVisible: false,
      addEmpMvForm: {},
      editEmpMvForm: {},
      workId: null
    }
  },
  methods: {
    handleSizeChange(val) {
      this.pageSize = val
      this.init()
    },
    handleCurrentChange(val) {
      this.pageNum = val
      this.init()
    },
    init() {
      this.getRequest('/per/mv/' + this.pageNum + '/' + this.pageSize).then(res => {
        console.log(res)
        this.tableData = res.data.obj.list
        console.log(this.tableData)
      })
    },
    showEdit(item) {
      this.editDialogVisible = true
      this.editEmpMvForm = item
    },
    del(id) {
      this.deleteRequest('/per/mv/delete/' + id).then(res => {
        this.$message.success(res.data.msg)
        this.init()
      })
    },
    add() {
      this.putRequest('/per/mv/add/' + this.workId, this.addEmpMvForm).then(res => {
        this.$message.success(res.data.msg)
        if (res.data.status === 200) {
          this.addDialogVisible = false
          this.init()
        }
      })
    },
    update() {
      this.putRequest('/per/mv/modify', this.editEmpMvForm).then(res => {
        this.$message.success(res.data.msg)
        this.editDialogVisible = false
      }).catch(error => {
        this.init()
      })
    }
  },
  created() {
    this.init()
  }
}
</script>

<style lang='less' scoped></style>
