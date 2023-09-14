<template>
  <div>
    <img :src='bg' alt='' class='wave' />
    <div class='container'>
      <div class='img'>
        <img :src='img_3' alt='' />
      </div>
      <div class='login-box'>
        <div class='form'>
          <img :src='avatar' alt='登录' class='avatar' />
          <h2>欢迎光临</h2>
          <div :class='userDivClass'>
            <div class='icon'>
              <i class='fa fa-user'></i>
            </div>
            <div class='userdiv'>
              <h5>账户</h5>
              <input
                type='text'
                v-model='LoginruleForm.username'
                class='input'
                @focus='userNameFocus'
                @blur='userNameBlur'
              />
            </div>
          </div>
          <div :class='passDivClass'>
            <div class='icon'>
              <i class='fa fa-lock'></i>
            </div>
            <div>
              <h5>密码</h5>
              <input
                type='password'
                v-model='LoginruleForm.password'
                class='input'
                @focus='passNameFocus'
                @blur='passNameBlur'
                @keydown.enter='submitForm'
              />
            </div>
          </div>
          <!-- <a href="#">忘记密码?</a> -->
          <button class='btn' @click='submitForm' style='margin-top: 10px;'>登录</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// 导入login页面需要的图片
import bg from '../assets/img/bg.png'
import avatar from '../assets/img/avatar.svg'
import img_3 from '../assets/img/img-3.svg'

export default {
  data() {
    return {
      bg,
      avatar,
      img_3,
      userDivClass: 'input-group',
      passDivClass: 'input-group',
      LoginruleForm: {
        username: '',
        password: ''
      }
    }
  },
  methods: {
    submitForm() {
      if (
        !this.LoginruleForm.username &&
        this.LoginruleForm.username.trim() === '' ||
        !this.LoginruleForm.password &&
        this.LoginruleForm.password.trim() === ''
      ) {
        return this.$message.warning('请正确填写账号和密码！')
      }
      this.postRequest('/login', {
        username: this.LoginruleForm.username,
        password: this.LoginruleForm.password
      }).then(resp => {
        if (resp.data.status === 200) {
          this.$store.commit('login', resp.data.obj)
          this.$store.dispatch('initData')
          this.$router.replace('/home')
          this.$message.success('登入成功')
        } else {
          this.$message.error('账号或者密码错误！')
        }
      })
    },
    userNameFocus() {
      this.userDivClass = 'input-group focus'
    },
    userNameBlur() {
      if (
        this.LoginruleForm.username &&
        this.LoginruleForm.username.trim() !== ''
      ) {
        return
      }
      this.userDivClass = 'input-group'
    },
    passNameFocus() {
      this.passDivClass = 'input-group focus'
    },
    passNameBlur() {
      if (
        this.LoginruleForm.password &&
        this.LoginruleForm.password.trim() !== ''
      ) {
        return
      }
      this.passDivClass = 'input-group'
    }
  },
}
</script>

<style lang='less' scoped>
* {
  padding: 0;
  margin: 0;
  box-sizing: border-box;
}

body {
  font-family: 'Roboto', sans-serif;
}

.wave {
  position: fixed;
  height: 100%;
  left: 0;
  bottom: 0;
  z-index: -1;
}

.container {
  width: 100vw;
  height: 100vh;
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  grid-gap: 18rem;
  padding: 0 2rem;
}

.img {
  display: flex;
  justify-content: flex-end;
  align-items: center;
}

.img img {
  width: 500px;
}

.login-box {
  display: flex;
  align-items: center;
  text-align: center;
}

.form {
  width: 360px;
}

.avatar {
  width: 100px;
}

.form h2 {
  font-size: 2.9rem;
  text-transform: uppercase;
  margin: 15px 0;
  color: #999;
}

.input-group {
  position: relative;
  display: grid;
  grid-template-columns: 7% 93%;
  margin: 25px 0;
  padding: 5px 0;
  border-bottom: 2px solid #d9d9d9;
}

.input-group:nth-child(1) {
  margin-bottom: 4px;
}

.input-group:before,
.input-group:after {
  content: '';
  position: absolute;
  bottom: -2px;
  width: 0;
  height: 2px;
  background-color: #38d39f;
  transition: 0.5s;
}

.input-group:after {
  right: 50%;
}

.input-group:before {
  left: 50%;
}

.icon {
  display: flex;
  justify-content: center;
  align-items: center;
}

.icon i {
  color: #d9d9d9;
  transition: 0.5s;
}

.input-group > div {
  position: relative;
  height: 45px;
}

.input-group > div > h5 {
  position: absolute;
  left: 10px;
  top: 50%;
  transform: translateY(-50%);
  color: #d9d9d9;
  font-size: 18px;
  transition: 0.3s;
}

.input-group.focus .icon i {
  color: #38d39f;
}

.input-group.focus div h5 {
  top: -5px;
  font-size: 15px;
}

.input-group.focus:after,
.input-group.focus:before {
  width: 50%;
}

.input {
  position: absolute;
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
  border: none;
  outline: none;
  background: none;
  padding: 0.5rem 0.7rem;
  font-size: 1.2rem;
  color: #555;
  font-family: 'Roboto', sans-serif;
}

a {
  display: block;
  text-align: right;
  text-decoration: none;
  color: #999;
  font-size: 0.9rem;
  transition: 0.3s;
}

a:hover {
  color: #38d39f;
}

.btn {
  text-align: center;
  display: block;
  width: 100%;
  height: 50px;
  border-radius: 25px;
  margin: 1rem 0;
  font-size: 1.2rem;
  outline: none;
  border: none;
  background-image: linear-gradient(to right, #32be8f, #38d39f, #32be8f);
  cursor: pointer;
  color: #fff;
  text-transform: uppercase;
  font-family: 'Roboto', sans-serif;
  background-size: 200%;
  transition: 0.5s;
}

.btn:hover {
  background-position: right;
}

.copyright {
  position: absolute;
  width: 100%;
  height: 50px;
  bottom: 2px;
  color: #38d39f;
  text-align: center;
  font-size: 18px;
  font-family: 'Roboto', sans-serif;
}

/*媒体查询*/
@media screen and (max-width: 1080px) {
  .container {
    grid-gap: 9rem;
  }
}

@media screen and (max-width: 1024px) {
  form {
    width: 290px;
  }

  form h2 {
    font-size: 2.4rem;
    margin: 8px 0;
  }

  .img img {
    width: 360px;
  }
}

@media screen and (max-width: 768px) {
  .wave {
    display: none;
  }

  .img {
    display: none;
  }

  .container {
    grid-template-columns: 1fr;
  }

  .login-box {
    justify-content: center;
  }
}
</style>
