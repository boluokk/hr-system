<template>
  <div>
    <el-breadcrumb separator="/" style="margin-bottom:15px;">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>员工培训</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card shadow="always" :body-style="{ padding: '20px' }">
      <div slot="header"></div>
      <el-table :data="tableData" style="width: 100%">
        <el-table-column prop="employeeName" label="名称" width="90">
        </el-table-column>
        <el-table-column prop="traincontent" label="培训内容" width="180">
        </el-table-column>
        <el-table-column label="时间" width="140">
          <template slot-scope="scope">
            {{ scope.row.traindate | dateFormat }}
          </template>
        </el-table-column>
        <el-table-column prop="remark" label="具体"> </el-table-column>
        <el-table-column label="名称" width="200">
          <template slot-scope="scope">
            <el-button
              type="info"
              icon="el-icon-edit"
              round
              @click="showEdit"
            ></el-button>
            <el-popconfirm
              confirm-button-text="好的"
              cancel-button-text="不用了"
              icon="el-icon-info"
              icon-color="red"
              title="这是一段内容确定删除吗？"
              @confirm="del(scope.row.id)"
            >
              <el-button
                type="danger"
                icon="el-icon-delete"
                slot="reference"
                round
              ></el-button>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
      <el-button
        type="primary"
        size="default"
        @click="showAdd"
        style="margin-top: 15px;"
        >添加</el-button
      >
      <el-dialog
        title="提示"
        :visible.sync="dialogVisible"
        width="30%"
      >
        <span>这是一段信息</span>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="dialogVisible = false"
            >确 定</el-button
          >
        </span>
      </el-dialog>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      tableData: [],
      visible2: false,
      dialogVisible: false
    }
  },
  methods: {
    init() {
      this.getRequest('/per/train/').then(res => {
        this.tableData = res.data.obj
      })
    },
    showEdit() {
      this.dialogVisible = true
    },
    showAdd() {},
    del(id) {
      console.log(id)
    }
  },
  created() {
    this.init()
  }
}
</script>

<style lang="less"></style>
