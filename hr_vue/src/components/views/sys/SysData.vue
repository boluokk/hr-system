<template>
  <div id='app'>
    <el-breadcrumb separator='/' style='margin-bottom:15px;'>
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>备份恢复数据库</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>
      <el-button type='success' class='record_cls' icon='el-icon-refresh' @click='showDialog'>恢复数据库
      </el-button>
      <el-popconfirm
        confirm-button-text='好的'
        cancel-button-text='不用了'
        icon='el-icon-info'
        icon-color='red'
        title='你确定要这么做吗？'
        @confirm='initDataBase'
      >
        <el-button slot='reference'
                   type='danger'
                   class='record_cls'
                   v-loading.fullscreen.lock='fullscreenLoading'
                   icon='el-icon-close'>初始化数据库
        </el-button>
      </el-popconfirm>
      <el-button type='primary' icon='el-icon-download' class='record_cls' @click='downloadBackup'>备份数据库
      </el-button>
      <el-table
        :data='tableData'
        style='width: 100%'>
        <el-table-column
          prop='tableName'
          label='表名'>
        </el-table-column>
        <el-table-column
          prop='tableSize'
          label='表大小(MB)'>
        </el-table-column>
        <el-table-column
          prop='tableRows'
          label='表行数'>
        </el-table-column>
      </el-table>
    </el-card>
    <el-dialog
      title='恢复数据库'
      :visible.sync='dialogVisible'
      width='30%'>
      <div>
        恢复方式：
        <el-radio v-model='uploadOrSelectRadio' label='1' border>自己上传</el-radio>
        <el-radio v-model='uploadOrSelectRadio' label='2' border>历史版本</el-radio>
      </div>
      <div class='radio_cls'>
        <div :style="{display: uploadOrSelectRadio === '1' ? 'block' : 'none'}">
          <el-upload
            class='upload-demo'
            drag
            show-file-list
            action='/sys/data/upload'
            v-loading.fullscreen.lock='fullscreenLoading'
            :http-request='uploadFileHandle'>
            <i class='el-icon-upload'></i>
            <div class='el-upload__text'>将文件拖到此处，或<em>点击上传</em></div>
            <div class='el-upload__tip' slot='tip'>只能上传sql文件 (上传后会覆盖当前数据库)</div>
          </el-upload>
        </div>
        <div :style="{display: uploadOrSelectRadio === '2' ? 'block' : 'none'}">
          选择历史版本：
          <el-select v-model='selectValue' placeholder='请选择'>
            <el-option
              v-for='item in options'
              :key='item.value'
              :label='item.label'
              :value='item.value'>
            </el-option>
          </el-select>
          <el-button type='primary' style='margin-left: 50px' @click='versionCommit'
                     v-loading.fullscreen.lock='fullscreenLoading'>确定
          </el-button>
        </div>
      </div>
    </el-dialog>
  </div>
</template>

<script>

export default {
  data() {
    return {
      tableData: [],
      dialogVisible: false,
      uploadOrSelectRadio: '2',
      options: [],
      selectValue: '',
      fullscreenLoading: false
    }
  },
  methods: {
    init() {
      this.getRequest('/sys/data/tables/info').then(res => {
        this.tableData = res.data.obj
      })
    },
    downloadBackup() {
      this.downloadFiles('/sys/data/backup', null, 'backup.sql')
    },
    showDialog() {
      this.getRequest('/sys/data/backup/version').then(res => {
        let data = res.data.obj
        let ans = []
        data.forEach(item => {
          ans.push({
            value: item,
            label: item
          })
        })
        this.options = ans
      })
      this.dialogVisible = true
    },
    versionCommit() {
      this.fullscreenLoading = true
      if (!(this.selectValue && this.selectValue.length > 0)) {
        return this.$message.warning('请选择版本')
      }
      this.getRequest('/sys/data/restore/version/' + this.selectValue)
        .finally(() => {
          this.fullscreenLoading = false
        })
    },
    uploadFileHandle(file) {
      this.fullscreenLoading = true
      this.uploadFiles('/sys/data/upload', file).then(res => {
        this.$message.success(res.data.msg)
      }).finally(() => {
        this.fullscreenLoading = false
      })
    },
    initDataBase() {
      this.fullscreenLoading = true
      this.getRequest('/sys/data/init/database').then(res => {
        this.$message.success(res.data.msg)
      }).finally(() => {
        this.fullscreenLoading = false
      })
    }
  },
  mounted() {
    this.init()
  }
}
</script>

<style lang='less' scoped>
.record_cls {
  float: right;
  margin-left: 15px;
}

.radio_cls {
  padding: 10px;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
