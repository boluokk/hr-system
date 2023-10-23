<template>
  <div>
    <el-container>
      <!-- 头部 -->
      <el-header>
        <span>
          <svg class='icon' aria-hidden='true'>
            <use xlink:href='#icon-boluo3'></use>
          </svg>
          <span style='font-size: 30px; font-family: 华文新魏,serif;'
          ><b>@人事管理</b></span
          >
        </span>

        <div style='display: flex;justify-content: center;align-items: center'>
          <span style='margin-right: 10px;'>{{curUser.username}}</span>
          <el-dropdown style='margin-right: 10px;' @command='commandHandel' trigger='click'>
          <span class='el-dropdown-link'>
            <el-avatar class='avatar' :src='currentHrImg'></el-avatar>
          </span>
            <el-dropdown-menu>
              <el-dropdown-item icon='el-icon-user' command='my'
              >我的
              </el-dropdown-item
              >
              <!--<el-dropdown-item icon='el-icon-s-tools' command='setting'>设置</el-dropdown-item>-->
              <el-dropdown-item icon='el-icon-chat-dot-square' command='msg'>
                聊天室
                <el-badge :value='msgCount' class='item' type='danger'></el-badge
                >
              </el-dropdown-item>
              <el-dropdown-item icon='el-icon-switch-button' command='logout'
              >退出
              </el-dropdown-item
              >
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </el-header>
      <!-- 菜单栏 -->
      <el-container>
        <el-aside width='230px'>
          <el-menu background-color='#00BFFF' unique-opened router :default-active=currentMenu>
            <el-submenu
              :index="index + ''"
              v-for='(item, index) in routes'
              :key='index'
            >
              <template slot='title'
              ><i :class=item.iconClass></i>{{ item.name }}
              </template
              >
              <el-menu-item-group
                v-for='(item2, index2) in item.children'
                :key='index2'
              >
                <el-menu-item :index='item2.path'>{{
                    item2.name
                  }}
                </el-menu-item>
              </el-menu-item-group>
            </el-submenu>
          </el-menu>
        </el-aside>
        <el-main>
          <div class='welcome' v-if="this.$router.currentRoute.path === '/home'">
            <el-empty></el-empty>
          </div>
          <el-dialog
            :visible.sync='userInfoVisible'
            width='55%'
            :before-close='userInfoWindowsClose'>
            <div>
              <el-descriptions title='账号信息' :column='3' size='medium' border>
                <el-descriptions-item label='账号'>{{ curUser.username }}</el-descriptions-item>
                <el-descriptions-item label='名称'>{{ curUser.name }}</el-descriptions-item>
                <el-descriptions-item label='手机号'>{{ curUser.phone }}</el-descriptions-item>
                <el-descriptions-item label='居住地'>{{ curUser.address }}</el-descriptions-item>
                <el-descriptions-item label='备注'>
                  <el-tag size='small'>{{ curUser.remark.length === 0 ? '无' : curUser.remark }}</el-tag>
                </el-descriptions-item>
                <el-descriptions-item label='密码'>**********</el-descriptions-item>
                <el-descriptions-item label='头像地址'>
                  {{ curUser.userFace.length > 0 ? curUser.userFace.substring(0, 100) + '....' : '' }}
                </el-descriptions-item>
              </el-descriptions>
              <el-form :model='curUser' ref='curUserRef' label-position='left'
                       label-width='auto' style='margin-top: 20px;'>
                <el-form-item label='名称' prop='name' required>
                  <el-input v-model='curUser.name' maxlength='10' show-word-limit style='width: 40%;'></el-input>
                </el-form-item>
                <el-form-item label='手机号' prop='phone' required>
                  <el-input v-model='curUser.phone' maxlength='11' show-word-limit style='width: 40%;'></el-input>
                </el-form-item>
                <el-form-item label='居住地' prop='address' required>
                  <el-input v-model='curUser.address' style='width: 40%;'></el-input>
                </el-form-item>
                <el-form-item label='头像地址' prop='userFace' required>
                  <el-input v-model='curUser.userFace' style='width: 40%;'></el-input>
                </el-form-item>
                <el-form-item label='密码' prop='password'>
                  <el-input v-model='curUser.password' maxlength='16' show-word-limit style='width: 40%;'></el-input>
                </el-form-item>
                <el-form-item label='备注' prop='remark'>
                  <el-input v-model='curUser.remark' type='textarea' :rows='2' maxlength='144' show-word-limit
                            style='width: 40%;'></el-input>
                </el-form-item>
              </el-form>
            </div>
            <span slot='footer' class='dialog-footer'>
                <el-button @click='userInfoVisible = false'>知道了</el-button>
                <el-button type='danger' @click='changeUserInfo'>更改</el-button>
            </span>
          </el-dialog>
          <router-view></router-view>
        </el-main>
      </el-container>

    </el-container>
  </div>
</template>

<script>

export default {
  data() {
    return {
      currentMenu: '',
      userInfoVisible: false,
      currentHrImg: this.$store.state.user.userFace,
      curUser: JSON.parse(window.sessionStorage.getItem(('user')))
    }
  },
  methods: {
    changeUserInfo() {
      this.$refs['curUserRef'].validate((valid) => {
        if (valid) {
          if (!this.curUser.password) {
            this.curUser.password = ''
          }
          this.putRequest('/sys/hr/modify', this.curUser).then(res => {
            if (res.data.status === 200) {
              this.$message.success(res.data.msg)
              if (this.curUser.password && this.curUser.password.length > 0) {
                this.logoutHandle()
              } else {
                this.userInfoVisible = false
              }
            }
          })
        } else {
          this.$message.error('有内容未填写完整!')
          return false
        }
      })
    },
    commandHandel(command) {
      if (command === 'my') {
        this.userInfoVisible = true
      } else if (command === 'setting') {
      } else if (command === 'logout') {
        this.logout()
      } else if (command === 'msg') {
        this.$router.push('/chat')
      }
    }
    ,
    logout() {
      this.$confirm('此操作将注销, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          this.logoutHandle()
        })
        .catch(() => {
          this.$message({
            type: 'info',
            message: '已取消'
          })
        })
    }
    ,
    logoutHandle() {
      this.getRequest('/logout').then(res => {
        if (res.data.status === 200) {
          this.$message.success('注销成功!')
          window.sessionStorage.removeItem('user')
          this.$store.state.routes = []
          this.$router.replace('/')
        }
      })
    },
    userInfoWindowsClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done()
        })
        .catch(_ => {
        })
    },
    getUrl() {
      let currentUrl = window.location.href
      this.currentMenu = currentUrl.split('#')[1]
    }
  },
  computed: {
    routes() {
      return this.$store.state.routes
    },
    msgCount() {
      return this.$store.state.messageCount
    }
  },
  watch: {},
  created() {
    this.getUrl()
  }
}
</script>

<style lang='less' scoped>
.el-header {
  background-color: #00BFFF;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.icon {
  width: 35px;
  height: 30px;
  vertical-align: -0.15em;
  fill: currentColor;
  overflow: hidden;
}

.welcome {
  //font-family: 华文新魏,serif;
  font-size: 50px;
  margin: 50px 500px;
}

.avatar {
  cursor: pointer;
}
</style>
