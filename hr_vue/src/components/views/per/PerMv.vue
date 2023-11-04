<template>
  <div>
    <el-breadcrumb separator='/' style='margin-bottom:15px;'>
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>员工调动</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card shadow='always' :body-style="{ padding: '20px' }">
      <div slot='header'></div>
      <el-table :data='tableData' style='width: 100%'>
        <el-table-column prop='employee.name' label='员工名称' width='90'>
        </el-table-column>
        <el-table-column prop='reason' label='原因' width='400'>
        </el-table-column>
        <el-table-column label='时间' width='180'>
          <template slot-scope='scope'>
            {{ scope.row.removeDate | dateFormat }}
          </template>
        </el-table-column>
        <el-table-column prop='beforeDepartmentName' label='旧部门' width='90'></el-table-column>
        <el-table-column prop='beforeJobName' label='旧职位' width='90'></el-table-column>
        <el-table-column prop='afterDepartmentName' label='新部门' width='90'></el-table-column>
        <el-table-column prop='afterJobName' label='新职位' width='90'></el-table-column>
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
        @click='addDialogVisible = true;getDepartmentAndJobLevel()'
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
            <el-date-picker type='date' placeholder='选择日期'
                            v-model='editEmpMvForm.removeDate'
                            value-format='yyyy-MM-dd HH:mm:ss'
                            style='width: 100%;'></el-date-picker>
          </el-form-item>

          <el-form-item label='旧部门'>
            <el-select v-model='editEmpMvForm.beforeDepartmentId' placeholder='请选择'>
              <el-option
                v-for='item in departmentOptions'
                :key='item.id'
                :label='item.name'
                :value='item.id'>
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label='旧职称'>
            <el-select v-model='editEmpMvForm.beforeJobId' placeholder='请选择'>
              <el-option
                v-for='item in jobLeveOptions'
                :key='item.id'
                :label='item.name'
                :value='item.id'>
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label='新部门'>
            <el-select v-model='editEmpMvForm.afterDepartmentId' placeholder='请选择'>
              <el-option
                v-for='item in departmentOptions'
                :key='item.id'
                :label='item.name'
                :value='item.id'>
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label='新职称'>
            <el-select v-model='editEmpMvForm.afterJobId' placeholder='请选择'>
              <el-option
                v-for='item in jobLeveOptions'
                :key='item.id'
                :label='item.name'
                :value='item.id'>
              </el-option>
            </el-select>
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
        <el-form ref='addEmpSalaryFormRef'
                 :rules='addEmpSalaryRoles'
                 :model='addEmpMvForm'
                 label-width='80px'
                 label-position='left'>
          <el-form-item label='员工号'>
            <el-input v-model='workId' maxlength='8' show-word-limit></el-input>
          </el-form-item>
          <el-form-item label='原因' prop='reason'>
            <el-input v-model='addEmpMvForm.reason'></el-input>
          </el-form-item>
          <el-form-item label='时间' prop='removeDate'>
            <el-date-picker type='date' placeholder='选择日期' v-model='addEmpMvForm.removeDate'
                            value-format='yyyy-MM-dd HH:mm:ss'
                            style='width: 100%;'></el-date-picker>
          </el-form-item>
          <el-form-item label='新部门' prop='afterDepartmentId'>
            <el-select v-model='addEmpMvForm.afterDepartmentId' placeholder='请选择'>
              <el-option
                v-for='item in departmentOptions'
                :key='item.id'
                :label='item.name'
                :value='item.id'>
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label='新职称' prop='afterJobId'>
            <el-select v-model='addEmpMvForm.afterJobId' placeholder='请选择'>
              <el-option
                v-for='item in jobLeveOptions'
                :key='item.id'
                :label='item.name'
                :value='item.id'>
              </el-option>
            </el-select>
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
      departmentOptions: [],
      jobLeveOptions: [],
      tableData: [],
      pageNum: 1,
      pageSize: 5,
      pageTotal: 0,
      addDialogVisible: false,
      editDialogVisible: false,
      addEmpMvForm: {},
      editEmpMvForm: {},
      workId: null,
      addEmpSalaryRoles: {
        removeDate: [
          { required: true, message: '请选择时间' }
        ],
        afterDepartmentId: [
          { required: true, message: '请选择部门' }
        ],
        afterJobId: [
          { required: true, message: '请选择职称' }
        ]
      }
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
      this.getDepartmentAndJobLevel()
    },
    getDepartmentAndJobLevel() {
      if (this.departmentOptions.length < 1) {
        this.getRequest('/per/mv/departments').then(res => {
          this.departmentOptions = res.data.obj
        })
      }
      if (this.jobLeveOptions.length < 1) {
        this.getRequest('/per/mv/joblevels').then(res => {
          this.jobLeveOptions = res.data.obj
        })
      }
    },
    del(id) {
      this.deleteRequest('/per/mv/delete/' + id).then(res => {
        this.$message.success(res.data.msg)
        this.init()
      })
    },
    add() {
      this.$refs['addEmpSalaryFormRef'].validate((valid) => {
        if (valid) {
          this.putRequest('/per/mv/add/' + this.workId, this.addEmpMvForm).then(res => {
            this.$message.success(res.data.msg)
            if (res.data.status === 200) {
              this.addDialogVisible = false
              this.init()
            }
          })
        } else {
          this.$message.warning('参数未填写完成!')
          return false
        }
      })
    },
    update() {
      this.putRequest('/per/mv/modify', this.editEmpMvForm).then(res => {
        this.$message.success(res.data.msg)
        this.editDialogVisible = false
        this.init()
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
