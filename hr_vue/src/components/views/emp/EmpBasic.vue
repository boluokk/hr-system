<template>
  <div>
    <el-breadcrumb separator='/'>
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>基本资料</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card shadow='always'>
      <div class='top'>
        <span>
          <el-input
            v-model='input'
            placeholder='请输入内容'
            size='mini'
            style='width: 350px;'
            prefix-icon='el-icon-search'
          ></el-input>
          <el-button
            type='primary'
            size='small'
            @click='search'
            :disabled='searchDisabled'
            icon='el-icon-search'
          >搜索</el-button
          >
          <el-button
            type='primary'
            size='small '
            @click='tipSearch'
            icon='el-icon-cpu'
          >高级搜索</el-button
          >
        </span>
        <span class='rightBtn'>
          <el-upload
            action='/basic/emp/import'
            :before-upload='beforeUpload'
            :show-file-list='false'
            :on-success='uploadSuccess'
            :on-error='uploadError'
            :disabled='uploadIsDis'
          >
            <el-button
              size='small'
              type='success'
              @click='importExcel'
              :icon='uploadIcon'
            >点击上传</el-button
            >
          </el-upload>
          <el-button
            type='success'
            size='small '
            @click='exportExcel'
            icon='el-icon-download'
          >下载数据</el-button
          >
          <el-button
            type='info'
            size='small '
            @click='showAddDia'
            icon='el-icon-circle-plus'
          >添加用户</el-button
          >
        </span>
      </div>
      <transition name='slide-fade'>
        <div
          v-show='isVisibleToTipSearch'
          style='border: 2px solid #70ebda;border-radius:5px;margin-top: 10px;'
        >
          <el-row :gutter='20'>
            <el-col
              :span='4'
              :offset='0'
              style='margin-top: 5px;margin-left: 10px;'
            >
              <span>政治面貌：</span>
              <el-select
                v-model='searchData.politicId'
                placeholder='请选择'
                style='width: 100px'
              >
                <el-option
                  v-for='item in politicDataTemp'
                  :key='item.id'
                  :label='item.name'
                  :value='item.id'
                >
                </el-option>
              </el-select>
            </el-col>
            <el-col :span='4' :offset='0' style='margin-top: 5px;'>
              <span>民族：</span>
              <el-select
                v-model='searchData.nationId'
                style='width: 100px'
                placeholder='请选择'
              >
                <el-option
                  v-for='item in nationData'
                  :key='item.id'
                  :label='item.name'
                  :value='item.id'
                >
                </el-option>
              </el-select>
            </el-col>
            <el-col :span='4' :offset='0' style='margin-top: 5px;'>
              <span>职位：</span>
              <el-select
                v-model='searchData.posId'
                placeholder='请选择'
                style='width: 100px'
              >
                <el-option
                  v-for='item in jobLeveData'
                  :key='item.id'
                  :label='item.name'
                  :value='item.id'
                >
                </el-option>
              </el-select>
            </el-col>
            <el-col :span='4' :offset='0' style='margin-top: 5px;'>
              <span>职称：</span>
              <el-select
                v-model='searchData.jobLevelId'
                style='width: 100px'
                placeholder='请选择'
              >
                <el-option
                  v-for='item in jobNameData'
                  :key='item.id'
                  :label='item.name'
                  :value='item.id'
                >
                </el-option>
              </el-select>
            </el-col>
            <el-col :span='7' :offset='0' style='margin-top: 12px;'>
              <span>雇佣方式：</span>
              <el-radio v-model='searchData.engageForm' label='劳动合同'
              >劳动合同
              </el-radio
              >
              <el-radio v-model='searchData.engageForm' label='劳务合同'
              >劳务合同
              </el-radio
              >
            </el-col>
          </el-row>
          <el-row :gutter='20' style='margin-bottom: 10px;'>
            <el-col :span='6' :offset='0' style='margin: 10px 0 0 10px'>
              <span>所属部门：</span>
              <el-select v-model='searchData.departmentId' placeholder='请选择'>
                <el-option
                  v-for='item in departmentData'
                  :key='item.id'
                  :label='item.name'
                  :value='item.id'
                >
                </el-option>
              </el-select>
            </el-col>
            <el-col :span='12' :offset='0' style='margin: 10px 0 0 10px'>
              <span>入职日期：</span>
              <el-date-picker
                v-model='value2'
                type='daterange'
                align='right'
                unlink-panels
                range-separator='至'
                start-placeholder='开始日期'
                end-placeholder='结束日期'
              >
              </el-date-picker>
            </el-col>
            <el-col :span='4' :offset='0' style='margin: 10px 0 0 10px'>
              <el-button size='mini' @click='cancelTipSearch'>取消</el-button>
              <el-button
                type='primary'
                icon='el-icon-search'
                size='mini'
                @click='tipSearchBtn'
              >搜索
              </el-button
              >
            </el-col>
          </el-row>
        </div>
      </transition>
      <el-table
        :data='tableData'
        border
        style='width: 100%;margin-top: 15px;'
        v-loading='loading'
      >
        <el-table-column type='selection' width='55' fixed></el-table-column>
        <el-table-column prop='name' label='姓名' width='90' fixed></el-table-column>
        <el-table-column prop='workId' label='工号' width='100' fixed></el-table-column>
        <el-table-column label='出生日期' width='170'>
          <template slot-scope='scope'>
            {{ scope.row.birthday | dateFormat }}
          </template>
        </el-table-column>
        <el-table-column prop='idCard' label='身份证号码' width='180'></el-table-column>
        <el-table-column prop='wedlock' label='婚姻状态' width='60'></el-table-column>
        <el-table-column label='名族' width='90'>
          <template slot-scope='scope'>
            {{ scope.row.nation.name }}
          </template>
        </el-table-column>
        <el-table-column prop='nativePlace' label='籍贯' width='100'>
        </el-table-column>
        <el-table-column label='政治面貌' width='110'>
          <template slot-scope='scope'>
            {{ scope.row.politicsStatus.name }}
          </template>
        </el-table-column>
        <el-table-column prop='email' label='电子邮件' width='170'>
        </el-table-column>
        <el-table-column prop='phone' label='手机号码' width='120'>
        </el-table-column>
        <el-table-column label='所属部门' width='100'>
          <template slot-scope='scope'>
            {{ scope.row.department.name }}
          </template>
        </el-table-column>
        <el-table-column prop='address' label='联系地址' width='250'>
        </el-table-column>
        <el-table-column label='职位' width='120'>
          <template slot-scope='scope'>
            {{ scope.row.position.name }}
          </template>
        </el-table-column>
        <el-table-column label='职称' width='120'>
          <template slot-scope='scope'>
            {{ scope.row.jobLevel.name }}
          </template>
        </el-table-column>
        <el-table-column prop='tiptopDegree' label='学历' width='60'>
        </el-table-column>
        <el-table-column prop='school' label='毕业院校' width='180'>
        </el-table-column>
        <el-table-column prop='specialty' label='大学专业' width='120'>
        </el-table-column>
        <el-table-column label='入职日期' width='170'>
          <template slot-scope='scope'>
            {{ scope.row.beginDate | dateFormat }}
          </template>
        </el-table-column>
        <el-table-column label='转正日期' width='170'>
          <template slot-scope='scope'>
            {{ scope.row.conversionTime | dateFormat }}
          </template>
        </el-table-column>
        <el-table-column label='合同起始日期' width='170'>
          <template slot-scope='scope'>
            {{ scope.row.beginContract | dateFormat }}
          </template>
        </el-table-column>
        <el-table-column label='合同终止日期' width='170'>
          <template slot-scope='scope'>
            {{ scope.row.endContract | dateFormat }}
          </template>
        </el-table-column>
        <el-table-column prop='engageForm' label='聘用方式' width='100'>

        </el-table-column>
        <el-table-column fixed='right' label='操作' width='200'>
          <template slot-scope='scope'>
            <el-button
              @click='handleClick(scope.row)'
              type='info'
              size='mini'
              icon='el-icon-setting'
            >编辑
            </el-button
            >
            <el-button
              type='danger'
              size='mini'
              icon='el-icon-delete'
              @click='remove(scope.row.id)'
            >删除
            </el-button
            >
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        @size-change='handleSizeChange'
        @current-change='handleCurrentChange'
        :current-page='1'
        :page-sizes='[1, 3, 5, 10]'
        :page-size='10'
        layout='total, sizes, prev, pager, next, jumper'
        :total='total'
        class='el_page_div'
      >
      </el-pagination>
    </el-card>
    <el-dialog :title='titleIsEOrA' :visible.sync='dialogVisible' width='95%'>
      <!-- 表单 -->
      <el-form
        ref='empFormRef'
        :model='employeeFrom'
        label-width='120px'
        label-position='right'
        :rules='empFormRoles'
      >
        <el-row :gutter='20'>
          <el-col :span='6'>
            <el-form-item prop='name' label='姓名:'>
              <el-input v-model='employeeFrom.name'></el-input>
            </el-form-item>
          </el-col>
          <el-col :span='6'>
            <el-form-item label='性别:'>
              <el-radio v-model='employeeFrom.gender' label='男'>男</el-radio>
              <el-radio v-model='employeeFrom.gender' label='女'>女</el-radio>
            </el-form-item>
          </el-col>
          <el-col :span='6'>
            <el-form-item prop='birthday' label='出生日期:' label-width='120px'>
              <el-date-picker
                value-format='yyyy-MM-dd HH:mm:ss'
                v-model='employeeFrom.birthday'
                type='date'
                placeholder='选择日期'
              >
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span='6'>
            <el-form-item prop='politicId' label='政治面貌:'>
              <el-select v-model='employeeFrom.politicId' placeholder='请选择'>
                <el-option
                  v-for='item in politicDataTemp'
                  :key='item.id'
                  :label='item.name'
                  :value='item.id'
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter='20'>
          <el-col :span='6'>
            <el-form-item prop='nationId' label='民族:'>
              <el-select v-model='employeeFrom.nationId' placeholder='请选择'>
                <el-option
                  v-for='item in nationData'
                  :key='item.id'
                  :label='item.name'
                  :value='item.id'
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span='6'>
            <el-form-item prop='nativePlace' label='籍贯:'>
              <el-input v-model='employeeFrom.nativePlace'></el-input>
            </el-form-item>
          </el-col>
          <el-col :span='6'>
            <el-form-item prop='email' label='邮箱:'>
              <el-input placeholder='请输入内容' v-model='employeeFrom.email'>
                <template slot='append'>qq.com</template>
              </el-input>
            </el-form-item
            >
          </el-col>
          <el-col :span='6'>
            <el-form-item prop='phone' label='电话:'>
              <el-input placeholder='请输入内容' v-model='employeeFrom.phone'>
              </el-input
              >
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter='20'>
          <el-col :span='6'>
            <el-form-item prop='address' label='住址:'>
              <el-input placeholder='请输入内容' v-model='employeeFrom.address'>
              </el-input
              >
            </el-form-item>
          </el-col>
          <el-col :span='6'>
            <el-form-item prop='departmentId' label='所属部门:'>
              <el-select v-model='employeeFrom.departmentId' placeholder='请选择'>
                <el-option
                  v-for='item in departmentData'
                  :key='item.id'
                  :label='item.name'
                  :value='item.id'
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span='6'>
            <el-form-item prop='jobLevelId' label='职称:'>
              <el-select v-model='employeeFrom.jobLevelId' placeholder='请选择'>
                <el-option
                  v-for='item in jobNameData'
                  :key='item.id'
                  :label='item.name'
                  :value='item.id'
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span='6'>
            <el-form-item prop='posId' label='职位:'>
              <el-select v-model='employeeFrom.posId' placeholder='请选择'>
                <el-option
                  v-for='item in jobLeveData'
                  :key='item.id'
                  :label='item.name'
                  :value='item.id'
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter='20'>
          <el-col :span='6'>
            <el-form-item label='雇佣方式:'>
              <el-radio v-model='employeeFrom.engageForm' label='劳动合同'
              >劳动合同
              </el-radio
              >
              <el-radio v-model='employeeFrom.engageForm' label='劳务合同'
              >劳务合同
              </el-radio
              >
            </el-form-item>
          </el-col>
          <el-col :span='6'>
            <el-form-item prop='tiptopDegree' label='学历:'>
              <el-select v-model='employeeFrom.tiptopDegree' placeholder='请选择'>
                <el-option
                  v-for='(item, idx) in tipTopDegreeTemp'
                  :key='idx'
                  :label='item.name'
                  :value='item.name'
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span='6'>
            <el-form-item prop='specialty' label='专业:'>
              <el-input v-model='employeeFrom.specialty'></el-input>
            </el-form-item>
          </el-col>
          <el-col :span='6'>
            <el-form-item prop='school' label='学校:'>
              <el-input v-model='employeeFrom.school'></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter='20'>
          <el-col :span='6' :offset='0'>
            <el-form-item prop='beginDate' label='入职日期:'>
              <el-date-picker
                value-format='yyyy-MM-dd HH:mm:ss'
                v-model='employeeFrom.beginDate'
                align='right'
                type='date'
                placeholder='选择日期'
              >
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span='6' :offset='0'>
            <el-form-item prop='conversionTime' label='转正日期:'>
              <el-date-picker
                value-format='yyyy-MM-dd HH:mm:ss'
                v-model='employeeFrom.conversionTime'
                align='right'
                type='date'
                placeholder='选择日期'
              >
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span='6' :offset='0'>
            <el-form-item prop='beginContract' label='合同起始日期:'>
              <el-date-picker
                value-format='yyyy-MM-dd HH:mm:ss'
                v-model='employeeFrom.beginContract'
                align='right'
                type='date'
                placeholder='选择日期'
              >
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span='6' :offset='0'>
            <el-form-item prop='endContract' label='合同终止日期:'>
              <el-date-picker
                value-format='yyyy-MM-dd HH:mm:ss'
                v-model='employeeFrom.endContract'
                align='right'
                type='date'
                placeholder='选择日期'
              >
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter='20'>
          <el-col :span='6' :offset='0'>
            <el-form-item prop='idCard' label='身份证:'>
              <el-input v-model='employeeFrom.idCard'></el-input>
            </el-form-item>
          </el-col>
          <el-col :span='6' :offset='0'>
            <el-form-item prop='wedlock' label='婚姻:'>
              <el-radio v-model='employeeFrom.wedlock' label='已婚'>已婚</el-radio>
              <el-radio v-model='employeeFrom.wedlock' label='未婚'>未婚</el-radio>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <span slot='footer' class='dialog-footer'>
        <el-button @click='cancel'>取 消</el-button>
        <el-button type='primary' @click='addUser'>确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { politdata } from '@/assets/js/DataHelp/politicsstatus'
import { nationData } from '@/assets/js/DataHelp/nation'
import { tiptopde } from '@/assets/js/DataHelp/tiptopdegree'

export default {
  data() {
    return {
      input: '',
      tableData: [],
      total: 0,
      pageSize: 10,
      pageNum: 1,
      dialogVisible: false,
      employeeFrom: {
        id: '',
        name: '',
        gender: '男',
        birthday: '',
        politicId: '',
        nationId: '',
        nativePlace: '',
        email: '',
        phone: '',
        address: '',
        departmentId: '',
        jobLevelId: '',
        posId: '',
        engageForm: '劳动合同',
        tiptopDegree: '',
        specialty: '',
        school: '',
        beginDate: '',
        conversionTime: '',
        beginContract: '',
        endContract: '',
        idCard: '',
        wedlock: '已婚',
        workId: ''
      },
      politicDataTemp: politdata,
      nationData: nationData,
      tipTopDegreeTemp: tiptopde,
      departmentData: [],
      jobNameData: [],
      jobLeveData: [],
      empFormRoles: {
        name: [{ required: true, message: '请输入相关参数', trigger: 'blur' }],
        gender: '男',
        birthday: [
          { required: true, message: '请输入相关参数', trigger: 'blur' }
        ],
        politicId: [
          { required: true, message: '请输入相关参数', trigger: 'blur' }
        ],
        nationId: [
          { required: true, message: '请输入相关参数', trigger: 'blur' }
        ],
        nativePlace: [
          { required: true, message: '请输入相关参数', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '请输入相关参数', trigger: 'blur' },
          {
            type: 'email',
            message: '请输入正确的邮箱地址',
            trigger: ['blur', 'change']
          }
        ],
        phone: [
          { required: true, message: '请输入相关参数', trigger: 'blur' },
          { min: 11, max: 11, message: '长度为 11 个字符', trigger: 'blur' }
        ],
        address: [
          { required: true, message: '请输入相关参数', trigger: 'blur' }
        ],
        departmentId: [
          { required: true, message: '请输入相关参数', trigger: 'blur' }
        ],
        jobLevelId: [
          { required: true, message: '请输入相关参数', trigger: 'blur' }
        ],
        posId: [{ required: true, message: '请输入相关参数', trigger: 'blur' }],
        engageForm: '劳动合同',
        tiptopDegree: [
          { required: true, message: '请输入相关参数', trigger: 'blur' }
        ],
        specialty: [
          { required: true, message: '请输入相关参数', trigger: 'blur' }
        ],
        school: [
          { required: true, message: '请输入相关参数', trigger: 'blur' }
        ],
        beginDate: [
          { required: true, message: '请输入相关参数', trigger: 'blur' }
        ],
        contractTerm: [
          { required: true, message: '请输入相关参数', trigger: 'blur' }
        ],
        conversionTime: [
          { required: true, message: '请输入相关参数', trigger: 'blur' }
        ],
        beginContract: [
          { required: true, message: '请输入相关参数', trigger: 'blur' }
        ],
        endContract: [
          { required: true, message: '请输入相关参数', trigger: 'blur' }
        ],
        idCard: [
          { required: true, message: '请输入相关参数', trigger: 'blur' }
        ],
        wedlock: '已婚'
      },
      // 添加还是编辑
      isEditOrAdd: 'edit',
      titleIsEOrA: '添加用户',
      loading: true,
      // 上传文件小icon
      uploadIcon: 'el-icon-upload',
      uploadIsDis: false,
      searchData: {
        politicId: null,
        nationId: null,
        departmentId: null,
        jobLevelId: null,
        posId: null,
        engageForm: null,
        conversionTime: null,
        beginDate: null
      },
      value2: [],
      isVisibleToTipSearch: false,
      searchDisabled: false
    }
  },
  methods: {
    tipSearchBtn() {
      const obj = {}
      for (const o in this.searchData) {
        if (this.searchData[o] !== null) {
          obj[o] = this.searchData[o]
        }
      }
      if (this.input !== '' && this.input !== null) {
        obj.name = this.input
      }
      this.initTipDataSer(obj)
    },
    initTipDataSer(obj) {
      this.postRequest(
        '/basic/emp/top/search/' + this.pageNum + '/' + this.pageSize,
        obj
      ).then(resp => {
        this.tableData = resp.data.obj.list
        this.total = resp.data.obj.total
      })
    },
    uploadError() {
      this.uploadIcon = 'el-icon-upload'
      this.$notify.error({
        title: '错误',
        message: '上传失败！'
      })
      this.uploadIsDis = false
      this.init()
    },
    uploadSuccess() {
      this.uploadIcon = 'el-icon-upload'
      this.$notify({
        title: '成功',
        message: '上传成功！',
        type: 'success'
      })
      this.uploadIsDis = false
      this.init()
    },
    beforeUpload() {
      this.uploadIcon = 'el-icon-loading'
      this.uploadIsDis = true
    },
    search() {
      if (this.input.length === 0) {
        return this.$message.warning('输入框不能为空!!')
      }
      this.getRequest('/basic/emp/byEmpName/' + this.input + '/' +
        this.pageNum + '/' + this.pageSize).then(res => {
        this.tableData = res.data.obj.list
        this.total = res.data.obj.total
      })
    },
    tipSearch() {
      if (!this.isVisibleToTipSearch) {
        this.initBaseData()
      }
      this.isVisibleToTipSearch = !this.isVisibleToTipSearch
    },
    cancelTipSearch() {
      this.isVisibleToTipSearch = false
    },
    handleClick(item) {
      this.isEditOrAdd = 'edit'
      this.initBaseData()
      this.employeeFrom.id = item.id
      this.employeeFrom.name = item.name
      this.employeeFrom.gender = item.gender
      this.employeeFrom.birthday = item.birthday
      this.employeeFrom.politicId = item.politicId
      this.employeeFrom.nationId = item.nationId + ''
      this.employeeFrom.nativePlace = item.nativePlace
      this.employeeFrom.email = item.email
      this.employeeFrom.phone = item.phone
      this.employeeFrom.address = item.address
      this.employeeFrom.departmentId = item.departmentId
      this.employeeFrom.jobLevelId = item.jobLevelId
      this.employeeFrom.posId = item.posId
      this.employeeFrom.engageForm = item.engageForm
      this.employeeFrom.tiptopDegree = item.tiptopDegree
      this.employeeFrom.specialty = item.specialty
      this.employeeFrom.school = item.school
      this.employeeFrom.conversionTime = item.conversionTime
      this.employeeFrom.beginContract = item.beginContract
      this.employeeFrom.endContract = item.endContract
      this.employeeFrom.idCard = item.idCard
      this.employeeFrom.wedlock = item.wedlock
      this.employeeFrom.beginDate = item.beginDate
      this.employeeFrom.workId = item.workId
      this.dialogVisible = true
    },
    importExcel() {
    },
    exportExcel() {
      window.open('/basic/emp/export', '_parent')
    },
    showAddDia() {
      this.dialogVisible = true
      this.isEditOrAdd = 'add'
      this.initBaseData()
    },
    initBaseData() {
      this.getRequest('/system/basic/department/').then(res => {
        this.departmentData = res.data.obj
      })
      this.getRequest('/system/basic/jobName/').then(res => {
        this.jobNameData = res.data.obj
      })
      this.getRequest('/system/basic/job/').then(res => {
        this.jobLeveData = res.data.obj
      })
    },
    init() {
      this.getRequest(
        '/basic/emp/' + this.pageNum + '/' + this.pageSize
      ).then(res => {
        console.log(res.data.obj.list)
        this.tableData = res.data.obj.list
        this.total = res.data.obj.total
        this.loading = false
      })
    },
    handleSizeChange(newSize) {
      this.pageSize = newSize
      if (!this.isVisibleToTipSearch) {
        this.init()
        return
      }
      this.tipSearchBtn()
    },
    handleCurrentChange(newNum) {
      this.pageNum = newNum
      if (!this.isVisibleToTipSearch) {
        this.init()
        return
      }
      this.tipSearchBtn()
    },
    addUser() {
      if (this.isEditOrAdd === 'add') {
        this.$refs.empFormRef.validate(vali => {
          if (!vali) return this.$message.error('请正确填写必填项！')
          this.putRequest('/basic/emp/add', this.employeeFrom).then(res => {
            this.$message.success(res.data.msg)
          })
          this.dialogVisible = false
          this.$refs.empFormRef.resetFields()
        })
      } else if (this.isEditOrAdd === 'edit') {
        this.putRequest('/basic/emp/modify', this.employeeFrom).then(res => {
          this.$message.success(res.data.msg)
          this.resetData()
          this.init()
          this.dialogVisible = false
        })
      }
    },
    cancel() {
      this.resetData()
      this.dialogVisible = false
    },
    resetData() {
      this.employeeFrom.id = ''
      this.employeeFrom.name = ''
      this.employeeFrom.gender = '男'
      this.employeeFrom.birthday = ''
      this.employeeFrom.politicId = ''
      this.employeeFrom.nationId = ''
      this.employeeFrom.nativePlace = ''
      this.employeeFrom.email = ''
      this.employeeFrom.phone = ''
      this.employeeFrom.address = ''
      this.employeeFrom.departmentId = ''
      this.employeeFrom.jobLevelId = ''
      this.employeeFrom.posId = ''
      this.employeeFrom.engageForm = '劳动合同'
      this.employeeFrom.tiptopDegree = ''
      this.employeeFrom.specialty = ''
      this.employeeFrom.school = ''
      this.employeeFrom.conversionTime = ''
      this.employeeFrom.beginContract = ''
      this.employeeFrom.endContract = ''
      this.employeeFrom.idCard = ''
      this.employeeFrom.wedlock = '已婚'
      this.employeeFrom.workId = ''
      this.employeeFrom.beginDate = ''
    },
    remove(id) {
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          this.deleteRequest('/basic/emp/delete/' + id).then(res => {
            this.$message.success(res.data.msg)
            this.init()
          })
        })
        .catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
    }
  },
  created() {
    this.init()
  },
  watch: {
    input(val) {
      if (val === '') {
        this.init()
      }
    },
    isEditOrAdd(val) {
      if (val === 'add') {
        this.titleIsEOrA = '添加用户'
      } else {
        this.titleIsEOrA = '修改用户'
      }
    },
    value2(val) {
      this.searchData.beginDate = val[0]
      this.searchData.conversionTime = val[1]
    },
    isVisibleToTipSearch(val) {
      this.searchDisabled = !this.searchDisabled
      if (!val) {
        this.init()
      }
    }
  }
}
</script>

<style lang='less' scoped>
.el-card {
  margin-top: 15px;
}

.el-button {
  margin-left: 15px;
}

.top {
  display: flex;
  justify-content: space-between;
}

body {
  margin: 0;
}

.rightBtn {
  display: flex;
}

.slide-fade-enter-active {
  transition: all 0.3s ease;
}

.slide-fade-leave-active {
  transition: all 0.8s cubic-bezier(1, 0.5, 0.8, 1);
}

.slide-fade-enter, .slide-fade-leave-to
  /* .slide-fade-leave-active for below version 2.1.8 */ {
  transform: translateX(10px);
  opacity: 0;
}
</style>
