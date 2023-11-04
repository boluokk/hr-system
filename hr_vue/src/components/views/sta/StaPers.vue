<template>
  <div id='app'>
    <el-breadcrumb separator='/' style='margin-bottom:15px;'>
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>人事信息统计</el-breadcrumb-item>
    </el-breadcrumb>
    <div class='gh_top'>
      <div class='gh_top_box'>
        <el-card class='box-card'>
          <div class='box-card_div'>
            <i class='el-icon-position el_icon_size'></i>
            <div class='gh_key_value_box'>
              <div class='gh_text_key'>当前在线数</div>
              <div class='gh_text_value'>{{ headerData.onlineCount }}</div>
            </div>
            <el-tooltip class='item'
                        effect='light'
                        content='当前在线数'
                        placement='right'>
              <i class='el-icon-question icon_question'></i>
            </el-tooltip>
          </div>
        </el-card>
      </div>
      <div class='gh_top_box'>
        <el-card class='box-card'>
          <div class='box-card_div'>
            <i class='el-icon-user el_icon_size'></i>
            <div class='gh_key_value_box'>
              <div class='gh_text_key'>操作员个数</div>
              <div class='gh_text_value'>{{ headerData.operatorCount }}</div>
            </div>
            <el-tooltip class='item'
                        effect='light'
                        content='操作员(人事)个数'
                        placement='right'>
              <i class='el-icon-question icon_question'></i>
            </el-tooltip>
          </div>
        </el-card>
      </div>
      <div class='gh_top_box'>
        <el-card class='box-card'>
          <div class='box-card_div'>
            <i class='el-icon-setting el_icon_size'></i>
            <div class='gh_key_value_box'>
              <div class='gh_text_key'>操作次数</div>
              <div class='gh_text_value'>{{ headerData.operateCount }}</div>
            </div>
            <el-tooltip class='item'
                        effect='light'
                        content='操作员操作次数'
                        placement='right'>
              <i class='el-icon-question icon_question'></i>
            </el-tooltip>
          </div>
        </el-card>
      </div>
    </div>

    <el-card class='box-card table_css'>
      <div id='online' class='gh_table'></div>
    </el-card>
    <el-card class='box-card table_css'>
      <div id='login' class='gh_table'></div>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      headerData: {
        onlineCount: 0,
        operateCount: 0,
        operatorCount: 0
      },
      online: {
        toolbox: {
          show: true,
          feature: {
            magicType: { type: ['line', 'bar'] },
            restore: {},
            saveAsImage: {}
          }
        },
        title: {
          left: 'center',
          text: '在线人数'
        },
        tooltip: {
          trigger: 'axis'
        },
        xAxis: {
          type: 'category',
          data: []
        },
        yAxis: {
          type: 'value'
        },
        series: [{
          data: [], type: 'line',
        }]
      },
      login: {
        toolbox: {
          show: true,
          feature: {
            magicType: { type: ['line', 'bar'] },
            restore: {},
            saveAsImage: {}
          }
        },
        tooltip: {
          trigger: 'axis'
        },
        title: {
          left: 'center',
          text: '登录次数'
        },
        xAxis: {
          type: 'category',
          data: []
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            data: [],
            type: 'line'
          }
        ]
      }
    }
  },
  created() {
    this.init()
  },
  mounted() {
  },
  methods: {
    createGh() {
      this.$echarts.init(document.getElementById('online')).setOption(this.online)
      this.$echarts.init(document.getElementById('login')).setOption(this.login)
    },
    async init() {
      this.getRequest('/sta/pers/header').then(res => {
        this.headerData = res.data.obj
      })
      this.getRequest('/sta/pers/online/1').then(res => {
        let temp = res.data.obj
        temp.forEach(item => {
          this.online.xAxis.data.push(item.time)
          this.online.series[0].data.push(item.count)
          // 获取数据成功后再调用, 不然会渲染不出来
          this.createGh()
        })
      })
      this.getRequest('/sta/pers/login/7').then(res => {
        let temp = res.data.obj
        temp.forEach(item => {
          this.login.xAxis.data.push(item.time)
          this.login.series[0].data.push(item.count)
        })
        this.createGh()
      })
    }
  }
}
</script>

<style scoped>
.gh_table {
  width: auto;
  height: 400px;
}

.gh_top {
  width: auto;
  height: 200px;
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
}

.gh_top_box {
  flex: 1;
  margin-right: 20px;
  margin-left: 20px;
}

.box-card_div {
  display: flex;
  align-items: center;
}

.el_icon_size {
  font-size: 60px;
}

.gh_text_key {
  font-size: small;
}

.gh_key_value_box {
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  padding: 15px;
}

.gh_text_value {
  margin-top: 15px;
  font-size: 25px;
  font-weight: bold;
}

.icon_question {
  position: absolute;
  right: 20px;
  top: 20px;
}

.box-card_div {
  position: relative;
}

.table_css {
  margin-top: 20px;
}
</style>
