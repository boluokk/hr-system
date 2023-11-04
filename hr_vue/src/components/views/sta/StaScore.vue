<template>
  <div id='app'>
    <el-breadcrumb separator='/' style='margin-bottom:15px;'>
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>员工积分统计</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>
      <el-row :gutter='20'>
        <el-col :span='6'>
          <div>
            <el-statistic
              group-separator=','
              :value='headerData.allCount'
              title='奖惩总人数'
            ></el-statistic>
          </div>
        </el-col>
        <el-col :span='6'>
          <div>
            <el-statistic title='奖惩人数比'>
              <template slot='formatter'>{{ headerData.rewardCount + '/' + headerData.punishmentCount }}</template>
            </el-statistic>
          </div>
        </el-col>
        <el-col :span='6'>
          <div>
            <el-statistic
              group-separator=','
              decimal-separator='.'
              :value='headerData.rewardCount'
              title='奖励人数'
            >
              <template slot='prefix'>
                <i class='el-icon-s-flag' style='color: red'></i>
              </template>
              <template slot='suffix'>
                <i class='el-icon-s-flag' style='color: blue'></i>
              </template>
            </el-statistic>
          </div>
        </el-col>
        <el-col :span='6'>
          <div>
            <el-statistic :value='headerData.punishmentCount' title='惩罚人数'></el-statistic>
          </div>
        </el-col>
      </el-row>
    </el-card>
    <div id='main'></div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      headerData: {
        allCount: 0,
        punishmentCount: 0,
        rewardCount: 0
      },
      employeeRecordData: {
        toolbox: {
          show: true,
          feature: {
            magicType: { type: ['line', 'bar'] },
            saveAsImage: {}
          }
        },
        title: {
          text: '最近奖惩个数'
        },
        tooltip: {
          trigger: 'axis'
        },
        legend: {
          data: ['惩罚', '奖励']
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          data: []
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            name: '奖励个数',
            type: 'line',
            stack: 'Total',
            data: [120, 132, 101, 134, 90, 230, 210]
          },
          {
            name: '惩罚个数',
            type: 'line',
            stack: 'Total',
            data: [220, 182, 191, 234, 290, 330, 310]
          }
        ]
      }
    }
  },
  methods: {
    init() {
      this.getRequest('/sta/score/header').then(res => {
        this.headerData = res.data.obj
      })
      this.getRequest('/sta/score/rewardPh/30').then(res => {
        let tXAxis = []
        let tSeries = [
          {
            name: '惩罚',
            type: 'line',
            stack: 'Total',
            data: []
          },
          {
            name: '奖励',
            type: 'line',
            stack: 'Total',
            data: []
          }
        ]
          let data = res.data.obj
          data.forEach(item => {
            if (item.rewardOrPunishment === '惩罚') {
              tXAxis.push(item.time)
              tSeries[0].data.push(item.count)
            } else {
              tSeries[1].data.push(item.count)
            }

          })
          this.employeeRecordData.xAxis.data = tXAxis
          this.employeeRecordData.series = tSeries
          this.createGh()
      })
    },
    createGh() {
      this.$echarts.init(document.getElementById('main')).setOption(this.employeeRecordData)
    }
  },
  created() {
    this.init()
  }
}
</script>

<style scoped>
#main {
  margin-top: 35px;
  width: auto;
  height: 500px;
}
</style>
