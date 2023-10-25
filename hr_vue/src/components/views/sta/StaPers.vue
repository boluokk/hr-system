<template>
  <div id='app'>
    <el-card class='box-card'>
      <div>
        <el-row :gutter="20">
          <el-col :span="6">
            <div>
              <el-statistic
                group-separator=","
                :precision="2"
                :value="value2"
                :title="title"
              ></el-statistic>
            </div>
          </el-col>
          <el-col :span="6">
            <div>
              <el-statistic title="男女比">
                <template slot="formatter">
                  456/2
                </template>
              </el-statistic>
            </div>
          </el-col>
          <el-col :span="6">
            <div>
              <el-statistic
                group-separator=","
                :precision="2"
                decimal-separator="."
                :value="value1"
                :title="title"
              >
                <template slot="prefix">
                  <i class="el-icon-s-flag" style="color: red"></i>
                </template>
                <template slot="suffix">
                  <i class="el-icon-s-flag" style="color: blue"></i>
                </template>
              </el-statistic>
            </div>
          </el-col>
          <el-col :span="6">
            <div>
              <el-statistic :value="like ? 521 : 520" title="Feedback">
                <template slot="suffix">
              <span @click="like = !like" class="like">
                <i
                  class="el-icon-star-on"
                  style="color:red"
                  v-show="!!like"
                ></i>
                <i class="el-icon-star-off" v-show="!like"></i>
              </span>
                </template>
              </el-statistic>
            </div>
          </el-col>
        </el-row>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      addressDate: [
        { value: 1, name: '成都' },
        { value: 1, name: '杭州' },
        { value: 2, name: '北京' },
        { value: 2, name: '上海' },
        { value: 1, name: '香港' }
      ],
      logDate: [
        { value: 1, name: 'admin' },
        { value: 1, name: 'xiaoyu' },
        { value: 2, name: 'laowang' },
        { value: 2, name: 'xiaozhang' }
      ]
    }
  },
  mounted() {
    this.init()
  }
  ,
  methods: {
    init() {
      let address = {
        toolbox: {
          show: true,
          feature: {
            mark: { show: true },
            restore: { show: true },
            saveAsImage: { show: true }
          }
        },
        title: {
          text: '所在城市',
          left: 'center'
        },
        tooltip: {
          trigger: 'item'
        },
        legend: {
          orient: 'vertical',
          left: 'left'
        },
        series: [
          {
            type: 'pie',
            radius: '50%',
            data: this.addressDate,
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      }
      let log = {
        toolbox: {
          show: true,
          feature: {
            mark: { show: true },
            restore: { show: true },
            saveAsImage: { show: true }
          }
        },
        title: {
          text: '操作次数',
          left: 'center'
        },
        tooltip: {
          trigger: 'item'
        },
        legend: {
          orient: 'vertical',
          left: 'left'
        },
        series: [
          {
            type: 'pie',
            radius: '50%',
            data: this.logDate,
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      }
      this.$echarts.init(document.getElementById('address')).setOption(address)
      this.$echarts.init(document.getElementById('log')).setOption(log)
    }
  }
}
</script>

<style scoped>

</style>
