package com.ibyte.sys.org.core.service;

import com.ibyte.common.util.IDGenerator;
import com.ibyte.sys.org.api.ISysOrgPersonComponentApi;
import com.ibyte.sys.org.core.api.ISysOrgPersonApi;
import com.ibyte.sys.org.core.entity.SysOrgAccount;
import com.ibyte.sys.org.core.entity.SysOrgPerson;
import com.ibyte.sys.org.core.repository.SysOrgPersonRepository;
import com.ibyte.sys.org.dto.PersonAccountVO;
import com.ibyte.sys.org.dto.SysOrgAccountVO;
import com.ibyte.sys.org.dto.SysOrgPersonVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 人员服务层
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Service
@RestController
@RequestMapping("/api/sys-org/sysOrgPerson")
@Transactional(readOnly = true, rollbackFor = {})
public class SysOrgPersonService extends AbstractElementCommonService<SysOrgPersonRepository, SysOrgPerson, SysOrgPersonVO>
        implements ISysOrgPersonApi, ISysOrgPersonComponentApi{

    @Autowired
    private SysOrgAccountService sysOrgAccountService;

    /**
     * 快捷增加人员与账号
     *
     * @param vo
     */
    @Transactional(rollbackFor = {})
    public void addPersonAccount(PersonAccountVO vo) {
        if (StringUtils.isBlank(vo.getFdId())) {
            vo.setFdId(IDGenerator.generateID());
        }
        // 账号信息
        SysOrgAccountVO account = new SysOrgAccountVO();
        // 默认是激活
        account.setFdIsActivated(true);
        convertToAccount(vo, account);
        Optional<SysOrgAccount> optional = sysOrgAccountService.findById(account.getFdId());
        if (optional.isPresent()) {
            sysOrgAccountService.update(account);
        } else {
            // 只有新增时才可以设置密码
            if (StringUtils.isNotBlank(vo.getFdPassword())) {
                account.setFdPassword(vo.getFdPassword());
            }
            sysOrgAccountService.add(account);
        }

        // 人员信息
        SysOrgPersonVO person = new SysOrgPersonVO();
        convertToPerson(vo, person);
        person.setFdAccount(account);
        add(person);

        // 更新账号表信息
        SysOrgAccount one = sysOrgAccountService.getOne(account.getFdId());
        if (one != null) {
            putPerson(one, person.getFdId());
            sysOrgAccountService.update(one);
        }
    }

    /**
     * 转换账号信息
     *
     * @param vo
     * @return
     */
    private void convertToAccount(PersonAccountVO vo, SysOrgAccountVO account) {
        if (StringUtils.isBlank(account.getFdId())) {
            account.setFdId(StringUtils.isBlank(vo.getFdAccountId()) ? IDGenerator.generateID() : vo.getFdAccountId());
        }
        account.setFdLoginName(vo.getFdLoginName());
        account.setFdNickName(vo.getFdNickName());
        account.setFdWechatNo(vo.getFdWechatNo());
        account.setFdShortNo(vo.getFdShortNo());
        account.setFdGender(vo.getFdGender());
        account.setFdMobileNo(vo.getFdMobileNo());
        account.setFdDefaultLang(vo.getFdDefaultLang());
        account.setFdCardNo(vo.getFdCardNo());
        account.setFdDoubleValidation(vo.getFdDoubleValidation());
        account.setFdAttendanceCardNo(vo.getFdAttendanceCardNo());
        account.setNullValueProps(vo.getNullValueProps());
        account.setFdIsActivated(vo.getFdIsAvailable());
    }

    /**
     * 转换人员信息
     *
     * @param vo
     * @return
     */
    private void convertToPerson(PersonAccountVO vo, SysOrgPersonVO person) {
        person.setFdId(vo.getFdId());
        person.setFdName(vo.getFdName());
        person.setFdOrder(vo.getFdOrder());
        person.setFdParent(vo.getFdParent());
        person.setFdIsAvailable(vo.getFdIsAvailable());
        person.setFdIsBusiness(vo.getFdIsBusiness());
        person.setFdNo(vo.getFdNo());
        person.setFdDescribe(vo.getFdDescribe());
        person.setFdEmail(vo.getFdEmail());
        person.setFdWorkPhone(vo.getFdWorkPhone());
        person.setFdStaffingLevel(vo.getFdStaffingLevel());
        person.setFdPosts(vo.getFdPosts());
        person.setNullValueProps(vo.getNullValueProps());
        person.setFdPersonType(vo.getFdPersonType());
        person.setFdKeyword(vo.getFdKeyword());
    }

    /**
     * 设置人员信息
     *
     * @param account
     * @param id
     */
    private void putPerson(SysOrgAccount account, String id) {
        SysOrgPerson person = findById(id).get();
        if (account.getFdDefPerson() == null) {
            account.setFdDefPerson(person);
        }

        List<SysOrgPerson> list = account.getFdPersons();
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(person);
        account.setFdPersons(list);
    }

    /**
     * 快捷修改人员与账号
     * <p>
     * 人员与账号在新增时绑定，后继不能解绑
     *
     * @param vo
     */
    @Transactional(rollbackFor = {})
    public void updatePersonAccount(PersonAccountVO vo) {
        // 账号信息
        SysOrgAccountVO account = new SysOrgAccountVO();
        convertToAccount(vo, account);
        sysOrgAccountService.update(account);
        // 人员信息
        SysOrgPersonVO person = new SysOrgPersonVO();
        convertToPerson(vo, person);
        update(person);
    }


}
