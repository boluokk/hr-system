<template>
  <div>
    <el-input
      placeholder='请输入角色英文名'
      v-model='rightsEnName'
      style='width: 300px;'
    >
      <template slot='prepend'>ROLE_</template>
    </el-input>
    <el-input
      placeholder='请输入角色中文名'
      v-model='rightsCnName'
      style='width: 300px; margin-left: 5px;'
    >
    </el-input>
    <el-button
      type='primary'
      @click='addRoles'
      style='margin-left: 20px;'
    >添加角色
    </el-button
    >

    <el-collapse
      v-model='selectRoles'
      :accordion='true'
      @change='handleChange'
      style='margin-top: 20px;'
    >
      <el-collapse-item
        :title='item.nameZh'
        v-for='(item, idx) in rightsData'
        :key='idx'
        :name="item.id + '-' + idx"
      >
        <!-- 树 -->
        <div class='treeAndBtn'>
          <el-tree
            ref='treeRef'
            show-checkbox
            node-key='id'
            :default-checked-keys='currentCheckNode'
            :data='rightsDataMenu'
            :props='defaultProps'
            style='width: 500px;'
          >
          </el-tree>
          <el-button size='mini' type='danger' @click='handleDelete(item.id)'
          >删除
          </el-button
          >
          <el-button size='mini' @click='handleEdit(item)'>编辑</el-button>
        </div>
        <el-button
          size='mini'
          type='warning'
          @click='editRights()'
          style='float: right; margin-bottom: 10px;'
        >修改权限
        </el-button
        >
      </el-collapse-item>
    </el-collapse>
    <!-- 编辑对话框 -->
    <el-dialog title='提示' :visible.sync='editDialogVisible' width='30%'>
      <!-- 修改内容表单的区域 -->
      <el-form
        :model='roleRuleForm'
        :rules='roleRules'
        ref='roleRuleForm'
        label-width='100px'
        class='demo-ruleForm'
      >
        <el-form-item label='角色名称' prop='nameZh'>
          <el-input v-model='roleRuleForm.nameZh'></el-input>
        </el-form-item>
      </el-form>
      <span slot='footer' class='dialog-footer'>
        <el-button @click='editDialogVisible = false'>取 消</el-button>
        <el-button type='primary' @click='changeRoleNameZh'>确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  props: ['rightsData', 'initRights', 'rightsDataMenu'],
  data() {
    return {
      rightsEnName: '',
      rightsCnName: '',
      defaultProps: {
        label: 'name',
        children: 'children'
      },
      selectRoles: '',
      currentCheckNode: [],
      editDialogVisible: false,
      roleRuleForm: {
        id: '',
        nameZh: ''
      },
      roleRules: {
        nameZh: [{ required: true, message: '请输入活动名称', trigger: 'blur' }]
      }
    }
  },
  methods: {
    async addRoles() {
      if (
        this.rightsEnName.trim() !== '' &&
        this.rightsEnName &&
        this.rightsCnName.trim() !== '' &&
        this.rightsCnName
      ) {
        this.putRequest('/system/basic/rights/add', {
          name: this.rightsEnName,
          nameZh: this.rightsCnName
        }).then(res => {
          this.initRights()
          this.$message.success(res.data.msg)
          this.rightsCnName = ''
          this.rightsEnName = ''
        })
      } else {
        this.$message.warning('请填写必要项！')
      }
    },
    handleChange(val) {
      const resultVal = val.split('-')
      if (val) {
        this.$refs.treeRef[resultVal[1]].setCheckedKeys([])
        this.getRequest('/system/basic/rights/' + resultVal[0]).then(res => {
          this.currentCheckNode = res.data.obj
        })
      }
    },
    handleEdit(item) {
      this.editDialogVisible = true
      this.roleRuleForm.nameZh = item.nameZh
      this.roleRuleForm.id = item.id
    },
    async handleDelete(id) {
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          this.deleteRequest('/system/basic/rights/delete/' + id).then(res => {
            this.$message.success(res.data.msg)
            this.initRights()
          })
        })
        .catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
    },
    editRights() {
      const roleMenuSplit = this.selectRoles.split('-')
      const menuIds = this.$refs.treeRef[roleMenuSplit[1]].getCheckedKeys()
      this.putRequest(
        '/system/basic/rights/modify/rights', {
          roleId: roleMenuSplit[0],
          menuIds: menuIds
        }
      ).then(res => {
        this.$message.success(res.data.msg)
        this.selectRoles = ''
      })
    },
    async changeRoleNameZh() {
      await this.$refs.roleRuleForm.validate(valid => {
        if (!valid) return this.$message('请填写必要项！')
        this.putRequest('/system/basic/rights/modify', {
          nameZh: this.roleRuleForm.nameZh,
          id: this.roleRuleForm.id
        }).then(res => {
          this.initRights()
          this.$message.success(res.data.msg)
          this.editDialogVisible = false
        })
      })
    },
  },
  // watch: {
  //   currentCheckNode: function(val, oldVal) {
  //     this.$refs.treeRef[1].setCheckedKeys([])
  //   }
  // }
}
</script>

<style lang='less' scoped>
.treeAndBtn .el-button {
  margin-left: 20px;
  float: right;
}

.el-tree {
  float: left;
}

.treeAndBtn {
  overflow: hidden;
  margin: 15px 0 15px 0;
}
</style>
