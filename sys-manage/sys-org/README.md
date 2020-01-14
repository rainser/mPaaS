# sys-org 
## 组织权限管理

### 组织架构基础数据模型
![组织架构基础数据模型](https://images.gitee.com/uploads/images/2019/1230/173721_27c0e789_1468963.png "组织架构基础模型.png")

#### 层级设计说明
fdHierarchyId 

```less
- x1302w0x1685c4931fccf9ef174bb8e4dcb809a2w0        // 根节点    [一级目录]
  - x1681668e83795a203c91d6946eca1586w0             // 二级节点  [二级目录]
    - x11baf3851fe3490589d03d247d69517fw0           // 三级节点  [三级目录]
      -x16c3bbd8558de9e52bea35f42969b975w0          // 四级节点  [四级目录]
```

``fdHierarchyId`` 目前默认最长长度`900`
 
> 注意:

- 目前系统自动生成的FdId长度36位
- 调整fdHierarchyId长度可自行调整[TreeEntity](https://gitee.com/ibyte/M-Pass/blob/master/common/common-core/src/main/java/com/ibyte/common/core/entity/TreeEntity.java)

**组织架构SQL整理**
- 更新机构和第一层级组织的层级关系
```sql
UPDATE SysOrgElement
SET fdParentOrg = NULL,
 fdHierarchyId = concat(concat('x', fdId), 'x'),
 fdTreeLevel = 1,
 fdLastModifiedTime = ? 1
WHERE
	fdIsAvailable = TRUE
AND fdTenantId = ? 2
AND (
	fdOrgType = 1
	OR fdOrgType > 1
	AND fdOrgType < 16
	AND fdParent IS NULL
)
AND (
	fdParentOrg IS NOT NULL
	OR fdHierarchyId <> concat(concat('x', fdId), 'x')
	OR fdHierarchyId IS NULL
)
```
- 获取组织架构层级关系（用于更新组织架构层级关系）
```sql
SELECT
	s.fdId,
	p.fdId,
	p.fdHierarchyId,
	p.fdParentOrg.fdId,
	p.fdOrgType,
	p.fdTreeLevel
FROM
	SysOrgElement s
INNER JOIN s.fdParent p
WHERE
	s.fdOrgType > 1
AND s.fdOrgType < 16
AND s.fdIsAvailable = TRUE
AND s.fdTenantId = : tenantId
AND (
	p.fdOrgType = 1
	AND (
		s.fdParentOrg IS NULL
		OR s.fdParentOrg <> p
	)
	OR p.fdOrgType > 1
	AND (
		s.fdParentOrg IS NULL
		AND p.fdParentOrg IS NOT NULL
		OR s.fdParentOrg IS NOT NULL
		AND p.fdParentOrg IS NULL
		OR s.fdParentOrg <> p.fdParentOrg
	)
	OR s.fdHierarchyId <> concat(
		concat(p.fdHierarchyId, s.fdId),
		'x'
	)
)
```

#### 自定义元数据完善
```java
public class LocalMetadataContributor
        implements MetadataContributor {
    private MetadataContributor[] contributors = new MetadataContributor[]{
            new InterfaceMetadataContributor(),   // 根据IField接口增强字段
            new ExtMetadataContributor(),         // 根据扩展配置增强字段
            new LanguageMetadataContributor(),    // 根据多语言特性动态生成多语言字段
            new ExtraMappingMetadataContributor() // Hibernate映射额外处理
    };

    @Override
    public void contribute(InFlightMetadataCollector metadataCollector,
                           IndexView jandexIndex) {
        for (MetadataContributor contributor : contributors) {
            contributor.contribute(metadataCollector, jandexIndex);
        }
    }
}
```
 * 自定义元数据完善，该服务在下面文件位置注册
 * ``/META-INF/services/org.hibernate.boot.spi.MetadataContributor``
 
- org.hibernate.boot.spi.MetadataContributor