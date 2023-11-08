<template>
  <div id='app'>
    <el-breadcrumb separator='/' style='margin-bottom:15px;'>
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>人事记录统计</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card class='box-card'>
      <div id='main' class='mainCls'></div>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      hrOperateInfo: {
        toolbox: {
          show: true,
          feature: {
            magicType: { type: ['line', 'bar'] },
            saveAsImage: {}
          }
        },
        title: {
          text: '最近人事操作次数'
        },
        tooltip: {
          trigger: 'axis'
        },
        legend: {
          data: []
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
        series: []
      }
    }
  },
  methods: {
    createGh() {
      this.$echarts.init(document.getElementById('main')).setOption(this.hrOperateInfo)
    },
    init() {
      let nameArr = []
      let tXAxis = []
      let tSeries = []
      this.getRequest('/sta/record/all/3').then(res => {
        if (res.data.status === 200) {
          let data = res.data.obj.data
          data.forEach((item, index) => {
            nameArr.push(item.name)
            tSeries.push({
              name: item.name,
              type: 'line',
              stack: 'Total',
              data: []
            })
            item.hrRecordStatistics.forEach(inItem => {
              if (index === 0) {
                tXAxis.push(this.dateFilter(inItem.time))
              }
              tSeries[index].data.push(inItem.count)
            })
          })
          this.hrOperateInfo.xAxis.data = tXAxis
          this.hrOperateInfo.series = tSeries
          this.hrOperateInfo.legend.data = nameArr
          this.createGh()
        }
      })
    }
  },
  mounted() {
    this.init()
    this.createGh()
  }
}

</script>

<style scoped>
.mainCls {
  width: auto;
  height: 500px;
}
</style>
