package zx.soft.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import zx.soft.crm.model.Address;

public interface AddressMapper {

	@Insert("insert into member_address (mid, name, province, city, county, address, phone)" //
			+ " values (#{mid}, #{name}, #{province}, #{city}, #{county}, #{address}, #{phone})")
	@Options(useGeneratedKeys = true, keyProperty = "address_id")
	void add(Address address);

	@Insert("insert into member_address (address_id ,mid, name, province, city, county, address, phone)" //
			+ " values (#{address_id},#{mid}, #{name}, #{province}, #{city}, #{county}, #{address}, #{phone})")
	@Options(useGeneratedKeys = true, keyProperty = "address_id")
	int addNoIncrement(Address address);

	@Delete("delete from member_address where mid = #{0} and address_id = #{1}")
	void delete(long mid, long address_id);

	@Select("select address_id, mid, name, province, city, county, address, phone" //
			+ " from member_address where address_id = #{0}")
	Address get(long address_id);

	@Select("select address_id, mid, name, province, city, county, address, phone" //
			+ " from member_address where mid = #{0}")
	List<Address> list(long mid);

	void update(Address address);

	@Delete("delete FROM `member_address` WHERE mid = #{0}")
	int deleteAll(long mid);


}
