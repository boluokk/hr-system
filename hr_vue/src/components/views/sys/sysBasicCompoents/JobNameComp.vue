<template>
  <div>
    <el-input
      v-model="jobNameValue"
      placeholder="+ 添加职称..."
      size="normal"
      style="width: 250px;"
    ></el-input>
    <el-select
      v-model="jobNameLevelValue"
      placeholder="职称等级"
      style="margin-left: 20px;"
      clearable
    >
      <el-option
        v-for="item in jobNameLevel"
        :key="item.value"
        :label="item.label"
        :value="item.value"
      >
      </el-option>
    </el-select>
    <el-button
      type="primary"
      @click="subAddJobValue"
      icon="el-icon-plus"
      style="margin-left: 15px;"
      >添加</el-button
    >
    <el-table :data="jobNameTableData" style="width: 100%">
      <el-table-column prop="id" label="编号" width="180"> </el-table-column>
      <el-table-column prop="name" label="姓名" width="180"> </el-table-column>
      <el-table-column prop="titleLevel" label="级别"> </el-table-column>
      <el-table-column prop="createDate" label="创建时间">
        <template slot-scope="scope">
          {{ scope.row.createDate}}
        </template>
      </el-table-column>
      <el-table-column label="操作" width='180'>
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.row.id)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <el-dialog title="提示" :visible.sync="dialogVisible" width="30%">
      <el-form
        :model="jobNameValidateForm"
        ref="dynamicValidateForm"
        label-width="100px"
      >
        <el-form-item
          prop="name"
          label="职位名称"
          :rules="[
            { required: true, message: '请输入职位名称', trigger: 'blur' }
          ]"
        >
          <el-input v-model="jobNameValidateForm.name"></el-input>
        </el-form-item>
        <el-form-item
          prop="titleLevel"
          label="职位等级"
          :rules="[
            { required: true, message: '请输入邮箱地址', trigger: 'blur' }
          ]"
        >
          <el-select
            v-model="jobNameValidateForm.titleLevel"
            placeholder="职称等级"
            style="margin-left: 20px;"
            clearable
          >
            <el-option
              v-for="item in jobNameLevel"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitUpDateJobName">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  props: {
    jobNameTableData: Array,
    initJobName: Function
  },
  data() {
    return {
      jobNameLevel: [
        {
          value: '正高级',
          label: '正高级'
        },
        {
          value: '副高级',
          label: '副高级'
        },
        {
          value: '初级',
          label: '初级'
        },
        {
          value: '中级',
          label: '中级'
        }
      ],
      jobNameLevelValue: '',
      jobNameValue: '',
      dialogVisible: false,
      jobNameValidateForm: {
        id: '',
        name: '',
        titleLevel: ''
      }
    }
  },
  methods: {
    subAddJobValue() {
      if (
        this.jobNameValue.trim() !== '' &&
        this.jobNameValue &&
        this.jobNameLevelValue.trim() !== '' &&
        this.jobNameLevelValue
      ) {
        this.putRequest('/system/basic/jobName/add', {
          name: this.jobNameValue,
          titleLevel: this.jobNameLevelValue
        }).then(res => {
          this.$message.success(res.data.msg)
          this.initJobName()
          this.jobNameValue = ''
          this.jobNameLevelValue = ''
        })
      } else {
        this.$message('请填写职称或选择职称等级！')
      }
    },
    handleEdit(jobNameDatas) {
      this.jobNameValidateForm.name = jobNameDatas.name
      this.jobNameValidateForm.titleLevel = jobNameDatas.titleLevel
      this.jobNameValidateForm.id = jobNameDatas.id
      this.dialogVisible = true
    },
    handleDelete(id) {
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          this.deleteRequest('/system/basic/jobName/delete/' + id).then(res => {
            this.$message.success(res.data.msg)
            this.initJobName()
          })
        })
        .catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
    },
    submitUpDateJobName() {
      this.$refs.dynamicValidateForm.validate(vali => {
        if (!vali) return this.$message.warning('请填写必要项！')
        this.putRequest(
          '/system/basic/jobName/modify',
          this.jobNameValidateForm
        ).then(res => {
          this.$message.success(res.data.msg)
          this.dialogVisible = false
          this.initJobName()
        })
      })
    }
  }
}
</script>

<style lang="less" scoped></style>
