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

        <el-dropdown style='margin-right: 15px;' @command='commandHandel' trigger='click'>
          <span class='el-dropdown-link'>
            <el-avatar class='avatar' :src='currentHrImg'></el-avatar>
          </span>
          <el-dropdown-menu slot='dropdown'>
            <el-dropdown-item icon='el-icon-user' command='my'
            >我的
            </el-dropdown-item
            >
            <el-dropdown-item icon='el-icon-s-tools' command='setting'
            >设置
            </el-dropdown-item
            >
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
      </el-header>
      <!-- 菜单栏 -->
      <el-container>
        <el-aside width='205px'>
          <el-menu background-color='#00BFFF' unique-opened router>
            <el-submenu
              :index="index + ''"
              v-for='(item, index) in routes'
              :key='index'
            >
              <template slot='title'
              ><i :class=item.iconcls></i>{{ item.name }}
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
                  {{ curUser.userface.length > 0 ? curUser.userface.substring(0, 100) + '....' : '' }}
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
                <el-form-item label='头像地址' prop='userface' required>
                  <el-input v-model='curUser.userface' style='width: 40%;'></el-input>
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
      userInfoVisible: false,
      currentHrImg: this.$store.state.user.userface,
      curUser: JSON.parse(window.sessionStorage.getItem(('user')))
    }
  },
  methods: {
    changeUserInfo() {
      this.$refs['curUserRef'].validate((valid) => {
        if (valid) {
          if (!this.curUser.password) { this.curUser.password = '' }
          this.putRequest('/sys/hr/', this.curUser).then(res => {
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
      this.$confirm('此操作将退出注销, 是否继续?', '提示', {
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
  watch: {}
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
