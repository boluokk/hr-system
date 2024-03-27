<template>
  <div>
    <el-input
      v-model='addJobValue'
      placeholder='+ 添加职位...'
      size='normal'
      style='width: 250px;'
    ></el-input>
    <el-button
      type='primary'
      @click='subAddJobValue'
      icon='el-icon-plus'
      style='margin-left: 15px;'
    >添加
    </el-button
    >
    <!-- 删除多项按钮 -->
    <el-button type='danger' @click='deleteMany'>批量删除</el-button>
    <el-table
      :data='position'
      style='width: 100%;margin-top: 20px;text-align: center;'
      border
      stripe
      @selection-change='handleSelectionChange'
    >
      >
      <el-table-column type='selection' width='55'></el-table-column>
      <el-table-column prop='id' label='编号' width='50'></el-table-column>
      <el-table-column prop='name' label='职位名称' width='150'>
      </el-table-column>
      <el-table-column prop='createdate' label='创建时间' width='250'>
        <template slot-scope='scope'>
          {{ scope.row.createDate }}
        </template>
      </el-table-column>
      <el-table-column prop='enabled' label='是否开启'>
        <template slot-scope='scope'>
          <el-tag v-if='scope.row.enabled === true' type='success'>开启</el-tag>
          <el-tag v-if='scope.row.enabled === false' type='danger'
          >未开启
          </el-tag
          >
        </template>
      </el-table-column>
      <el-table-column label='操作'>
        <template slot-scope='scope'>
          <el-button size='mini' @click='handleEdit(scope.row)'>编辑</el-button>
          <el-button size='mini' type='danger' @click='handleDelete(scope.row)'
          >删除
          </el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <el-dialog
      title='添加职位'
      :visible.sync='dialogVisible'
      width='30%'
      center
    >
      <el-form
        :model='posRuleForm'
        :rules='posRules'
        ref='posRuleFormRef'
        label-width='90px'
      >
        <el-form-item label='编号' prop='id'>
          <el-input v-model='posRuleForm.id' disabled></el-input>
        </el-form-item>
        <el-form-item label='职位名称' prop='name'>
          <el-input v-model='posRuleForm.name'></el-input>
        </el-form-item>
      </el-form>
      <span slot='footer' class='dialog-footer'>
        <el-button @click='dialogVisible = false'>取 消</el-button>
        <el-button type='primary' @click='changePosNameHandel'>确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  // props: ['position'],
  props: {
    position: Array,
    initPos: Function
  },
  data() {
    return {
      addJobValue: '',
      selectIds: [],
      dialogVisible: false,
      posRuleForm: {
        id: '',
        name: ''
      },
      posRules: {
        name: [{ required: true, message: '请输入活动名称', trigger: 'blur' }]
      }
    }
  },
  methods: {
    // 添加新职位
    subAddJobValue() {
      if (this.addJobValue.trim() === '') {
        return this.$message.error('请填写职位名称！')
      }
      this.putRequest('/system/basic/job/add', {
        name: this.addJobValue
      }).then(res => {
        this.$message.success(res.data.msg)
        this.initPos()
        this.addJobValue = ''
      })
    },
    // 多项删除获取id
    handleSelectionChange(val) {
      this.selectIds = []
      val.forEach(item => {
        this.selectIds.push(item.id)
      })
    },
    // 编辑
    handleEdit(job) {
      this.dialogVisible = true
      this.posRuleForm.id = job.id
      this.posRuleForm.name = job.name
    },
    // 删除
    async handleDelete(job) {
      const result = await this.$confirm(
        '此操作将永久删除该文件, 是否继续?',
        '提示',
        {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }
      ).catch(err => err)
      if (result !== 'confirm') {
        return this.$message('删除取消！')
      }
      this.deleteRequest('/system/basic/job/delete/' + job.id).then(res => {
        this.$message.success(res.data.msg)
        this.initPos()
      })
    },
    changePosNameHandel() {
      this.$refs.posRuleFormRef.validate(valid => {
        if (valid) {
          this.putRequest('/system/basic/job/modify', this.posRuleForm).then(
            res => {
              this.$message.success(res.data.msg)
              this.initPos()
              this.dialogVisible = false
            }
          )
        } else {
          this.$message.error('请填写必要项！')
        }
      })
    },
    // 批量删除
    async deleteMany() {
      if (this.selectIds.length < 1) {
        return this.$message.warning('请选择职位！')
      }
      const result = await this.$confirm(
        '此操作将永久删除该文件, 是否继续?',
        '提示',
        {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }
      ).catch(err => err)
      if (result !== 'confirm') {
        return this.$message('删除取消！')
      }
      this.deleteRequest('/system/basic/job/delete/many/', this.selectIds).then(res => {
        this.$message.success(res.data.msg)
        this.initPos()
      })
    }
  }
}
</script>

<style lang='less' scoped></style>
