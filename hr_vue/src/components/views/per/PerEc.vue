<template>
  <div>
    <el-breadcrumb separator='/' style='margin-bottom:15px;'>
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>员工奖惩</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card shadow='always' :body-style="{ padding: '20px' }">
      <div slot='header'></div>
      <!-- card body -->
      <el-table :data='tableData' style='width: 100%'>
        <el-table-column prop='workId' label='工号' width='120'>
        </el-table-column>
        <el-table-column prop='employeeName' label='姓名' width='120'>
        </el-table-column>
        <el-table-column label='时间' width='200'>
          <template slot-scope='scope'>
            {{ scope.row.ecdate | dateFormat }}
          </template>
        </el-table-column>
        <el-table-column prop='ecreason' label='原因' width='120'>
        </el-table-column>
        <el-table-column prop='remark' label='奖惩级别' width='120'>
        </el-table-column>
        <el-table-column prop='ecpoint' label='分数' width='120'>
        </el-table-column>
        <el-table-column label='操作' width='250'>
          <template slot-scope='scope'>
            <el-button
              icon='el-icon-edit'
              size='mini'
              type='info'
              style='margin-right: 10px;'
              @click='showEdit(scope.row)'
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
                size='mini'
                slot='reference'
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
      <el-button type='primary' style='margin-top:15px;' @click='showAdd'
      >添加
      </el-button
      >
      <el-dialog title='添加' :visible.sync='dialogVisible2' width='30%'>
        <el-form
          label-position='left'
          label-width='80px'
          :model='formLabelAlign'
        >
          <el-form-item label='员工工号'>
            <el-input
              v-model='formLabelAlign.workId'
              maxlength='8'
              show-word-limit
            ></el-input>
          </el-form-item>
          <el-form-item label='奖惩原因'>
            <el-input v-model='formLabelAlign.ecreason'></el-input>
          </el-form-item>
          <el-form-item label='奖惩级别'>
            <el-autocomplete
              class='inline-input'
              v-model='state1'
              :fetch-suggestions='querySearch'
              placeholder='请输入内容'
              clearable
            ></el-autocomplete>
          </el-form-item>
          <el-form-item label='分数'>
            <el-input
              v-model='grade'
              disabled
              size='normal'
              clearable
            ></el-input>
          </el-form-item>
        </el-form>
        <span slot='footer'>
          <el-button @click='dialogVisible2 = false'>取 消</el-button>
          <el-button type='primary' @click='submit'>提交</el-button>
        </span>
      </el-dialog>
      <el-dialog
        title='提示'
        :visible.sync='dialogVisible'
        width='30%'
        @closed='dialogClosed'
      >
        <div class='inputBox'>
          <span>原因：</span>
          <el-input
            v-model='reason'
            size='normal'
            style='width: 202px;'
            clearable
          ></el-input>
        </div>
        <div class='inputBox'>
          <span>奖赏级别：</span>
          <el-autocomplete
            class='inline-input'
            v-model='state1'
            :fetch-suggestions='querySearch'
            placeholder='请输入内容'
          ></el-autocomplete>
        </div>
        <div class='inputBox'>
          <span>分数：</span>
          <el-input
            v-model='grade'
            disabled
            size='normal'
            style='width: 202px;'
            clearable
          ></el-input>
        </div>

        <span slot='footer' class='dialog-footer'>
          <el-button @click='dialogVisible = false'>取 消</el-button>
          <el-button type='primary' @click='eidtSubmit'>确 定</el-button>
        </span>
      </el-dialog>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      pageSize: 10,
      pageNum: 1,
      pageTotal: 0,
      tableData: [],
      dialogVisible: false,
      dialogVisible2: false,
      state1: '',
      restaurants: [
        {
          value: '小功'
        },
        {
          value: '大功'
        },
        {
          value: '嘉奖'
        },
        {
          value: '警告'
        }
      ],
      grade: 0,
      reason: '',
      id: '',
      formLabelAlign: {
        workId: '',
        ecpoint: 0,
        ecreason: '',
        remark: ''
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
    showAdd() {
      this.dialogVisible2 = true
    },
    init() {
      this.getRequest('/per/ec/' + this.pageNum + '/' + this.pageSize).then(res => {
        this.tableData = res.data.obj.list
      })
    },
    del(id) {
      this.deleteRequest('/per/ec/delete/' + id).then(res => {
        this.init()
        this.$message.success(res.data.msg)
      })
    },
    showEdit(item) {
      this.dialogVisible = true
      this.state1 = item.remark
      this.reason = item.ecreason
      this.id = item.id
    },
    querySearch(queryString, cb) {
      var restaurants = this.restaurants
      var results = queryString
        ? restaurants.filter(this.createFilter(queryString))
        : restaurants
      // 调用 callback 返回建议列表的数据
      cb(results)
    },
    createFilter(queryString) {
      return restaurant => {
        return (
          restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) ===
          0
        )
      }
    },
    eidtSubmit() {
      this.putRequest('/per/ec/modify', {
        id: this.id,
        ecreason: this.reason,
        remark: this.state1,
        ecpoint: this.grade
      }).then(res => {
        this.clearSomething()
        this.$message.success(res.data.msg)
        this.init()
        this.dialogVisible = false
      })
    },
    submit() {
      let workidReg = new RegExp('^[0-9]{8}$')
      if (
        workidReg.test(this.formLabelAlign.workId) &&
        this.state1 &&
        this.state1 !== '' &&
        this.formLabelAlign.ecreason &&
        this.formLabelAlign.ecreason !== ''
      ) {
        this.formLabelAlign.remark = this.state1
        this.formLabelAlign.ecpoint = this.grade
        this.putRequest('/per/ec/add', this.formLabelAlign).then(res => {
          if (res.data.status == 200) {
            this.$message.success(res.data.msg)
            this.init()
            this.dialogVisible2 = false
          } else {
            this.$message.error(res.data.msg)
          }
        })
      } else {
        this.$message.warning('必要项未填或者工号错误！！')
      }
    },
    clearSomething() {
      this.grade = 0
      this.state1 = ''
    },
    dialogClosed() {
      this.clearSomething()
    }
  },
  created() {
    this.init()
  },
  watch: {
    state1(val) {
      if (val == '小功') {
        this.grade = 10
      } else if (val == '大功') {
        this.grade = 20
      } else if (val == '嘉奖') {
        this.grade = 5
      } else if (val == '警告') {
        this.grade = -5
      } else {
        this.grade = 0
      }
    }
  }
}
</script>

<style lang='less' scoped>
.inputBox {
  width: 300px;
  display: flex;
  justify-content: space-between;
  margin-bottom: 10px;
}
</style>
