package com.example.bookstore.DAO;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface StaffDAO  {

//    @Autowired
//    private EntityManager entityManager;

//    public void themNhanVien(NhanVien nhanVien);
//    public NhanVien layNhanVien(String maNhanVien);
//    public void capNhatNhanVien(NhanVien nhanVien);
//    public void xoaNhanVien(String maNhanVien);
//    List<NhanVien> getAllNhanVien();
//
//    void saveOrUpdateNhanVien(NhanVien nhanVien);
//
//    NhanVien getNhanVienByMaNhanVien(String maNhanVien);

//    List<String> getRoleNames(String roleName);
//
//    List<String> getNhanVienByRoleName(String roleName);

//    List<String> getRoleNames(Long userId);

//    public List<String> getRoleNames(Long userId) {
//        String sql = "Select ur.appRole.roleName from " + UserRole.class.getName() + " ur " //
//                + " where ur.appUser.userId = :userId ";
//
//        Query query = this.entityManager.createQuery(sql, String.class);
//        query.setParameter("userId", userId);
//        return query.getResultList();
//    }
}
