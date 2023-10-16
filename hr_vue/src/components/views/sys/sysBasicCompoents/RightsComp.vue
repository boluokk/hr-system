<template>
  <div>
    <el-input
      placeholder="请输入角色英文名"
      v-model="rightsEnName"
      style="width: 300px;"
    >
      <template slot="prepend">ROLE_</template>
    </el-input>
    <el-input
      placeholder="请输入角色中文名"
      v-model="rightsCnName"
      style="width: 300px; margin-left: 5px;"
    >
    </el-input>
    <el-button
      type="primary"
      size="default"
      @click="addRoles"
      style="margin-left: 20px;"
      >添加角色</el-button
    >

    <el-collapse
      v-model="selectRoles"
      :accordion="true"
      @change="handleChange"
      style="margin-top: 20px;"
    >
      <el-collapse-item
        :title="item.namezh"
        v-for="(item, idx) in rightsData"
        :key="idx"
        :name="item.id + '-' + idx"
      >
        <!-- 树 -->
        <div class="treeAndBtn">
          <el-tree
            ref="treeRef"
            show-checkbox
            node-key="id"
            :default-checked-keys="currentCheckNode"
            :data="rightsData2"
            :props="defaultProps"
            style="width: 500px;"
          >
          </el-tree>
          <el-button size="mini" type="danger" @click="handleDelete(item.id)"
            >删除</el-button
          >
          <el-button size="mini" @click="handleEdit(item)">编辑</el-button>
        </div>
        <el-button
          size="mini"
          type="warning"
          @click="editRights()"
          style="float: right; margin-bottom: 10px;"
          >修改权限</el-button
        >
      </el-collapse-item>
    </el-collapse>
    <!-- 编辑对话框 -->
    <el-dialog title="提示" :visible.sync="editdialogVisible" width="30%">
      <!-- 修改内容表单的区域 -->
      <el-form
        :model="RoleruleForm"
        :rules="Rolerules"
        ref="RoleruleForm"
        label-width="100px"
        class="demo-ruleForm"
      >
        <el-form-item label="角色名称" prop="namezh">
          <el-input v-model="RoleruleForm.namezh"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editdialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="subChRoleNameZh">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  props: ['rightsData', 'initRights', 'rightsData2'],
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
      editdialogVisible: false,
      RoleruleForm: {
        id: '',
        namezh: ''
      },
      Rolerules: {
        namezh: [{ required: true, message: '请输入活动名称', trigger: 'blur' }]
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
          namezh: this.rightsCnName
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
      this.editdialogVisible = true
      this.RoleruleForm.namezh = item.namezh
      this.RoleruleForm.id = item.id
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
      const results = this.selectRoles.split('-')
      const arr = this.$refs.treeRef[results[1]].getCheckedKeys()
      arr.shift()
      if (arr.length >= 0 && arr) {
        let checkIds = '?'
        arr.forEach(item => {
          checkIds += 'ids=' + item + '&'
        })
        this.putRequest(
          '/system/basic/rights/reRights/' + results[0] + checkIds
        ).then(res => {
          this.$message.success(res.data.msg)
          this.selectRoles = ''
        })
      }
    },
    async subChRoleNameZh() {
      await this.$refs.RoleruleForm.validate(vali => {
        if (!vali) return this.$message('请填写必要项！')
        this.putRequest('/system/basic/rights/modify', {
          namezh: this.RoleruleForm.namezh,
          id: this.RoleruleForm.id
        }).then(res => {
          this.initRights()
          this.$message.success(res.data.msg)
          this.editdialogVisible = false
        })
      })
    }
  }
  // watch: {
  //   currentCheckNode: function(val, oldVal) {
  //     // console.log(this.$refs.treeRef[0])
  //     this.$refs.treeRef[1].setCheckedKeys([])
  //   }
  // }
}
</script>

<style lang="less" scoped>
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
