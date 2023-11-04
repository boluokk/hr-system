<template>
  <div id='app'>
    <el-breadcrumb separator='/' style='margin-bottom:15px;'>
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>操作日志管理</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>
      <div>
        <el-row :gutter='20'>
          <el-col :span='6'>
            <div>
              <el-statistic
                group-separator=','
                :precision='2'
                :value='headerData.operatorLogCount'
                title='总操作次数'
              ></el-statistic>
            </div>
          </el-col>
          <el-col :span='6'>
            <div>
              <el-statistic
                title='最近操作时间'>
                <template slot='formatter'>
                  {{ headerData.createDate }}
                </template>
              </el-statistic>
            </div>
          </el-col>
          <el-col :span='6'>
            <div>
              <el-statistic
                title='操作人'
              >
                <template slot='formatter'>
                  {{ headerData.operatorName }}
                </template>
              </el-statistic>
            </div>
          </el-col>
          <el-col :span='6'>
            <div>
              <el-statistic
                title='内容'>
                <template slot='formatter'>
                  {{ headerData.content }}
                </template>
              </el-statistic>
            </div>
          </el-col>
        </el-row>
      </div>
    </el-card>
    <el-card class='el_card_cls'>
      <el-table
        :data='tableData'
        style='width: 100%'>
        <el-table-column
          prop='createDate'
          label='时间'
          width='180'>
        </el-table-column>
        <el-table-column
          prop='content'
          label='内容'
          width='180'>
        </el-table-column>
        <el-table-column
          prop='address'
          label='地址'>
        </el-table-column>
        <el-table-column
          prop='event'
          label='请求路径'>
        </el-table-column>
        <el-table-column
          prop='type'
          label='请求类型'>
        </el-table-column>
      </el-table>
      <el-pagination
        @size-change='handleSizeChange'
        @current-change='handleCurrentChange'
        :current-page.sync='pageNum'
        :page-size='10'
        layout='total, prev, pager, next'
        :total='total'
        style='margin-top: 15px;'
      ></el-pagination>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      tableData: [],
      pageNum: 1,
      total: 0,
      pageSize: 10,
      headerData: {
        content: '..',
        createDate: '..',
        operatorLogCount: 0,
        operatorName: '..'
      }
    }
  },
  methods: {
    handleCurrentChange(newNum) {
      this.pageNum = newNum
      this.init()
    },
    handleSizeChange(newSize) {
      this.pageSize = newSize
      this.init()
    },
    init() {
      this.getRequest('/sys/log/header').then(res => {
        this.headerData = res.data.obj
      })
      this.getRequest('/sys/log/all/' + this.pageNum + '/' + this.pageSize).then(res => {
        this.tableData = res.data.obj.list
        this.total = res.data.obj.total
      })
    }
  },
  created() {
    this.init()
  }
}
</script>

<style lang='less' scoped>
.el_card_cls {
  margin-top: 20px;
}
</style>
