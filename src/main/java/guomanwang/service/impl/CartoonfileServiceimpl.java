package guomanwang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import guomanwang.domain.Cartoonfile;
import guomanwang.domain.CartoonfileExample;
import guomanwang.mapper.CartoonfileMapper;
import guomanwang.service.CartoonfileService;
@Service("CartoonfileServiceimpl")
public class CartoonfileServiceimpl implements CartoonfileService {

	@Autowired
	@Qualifier("CartoonfileMapper")
	private CartoonfileMapper cartoonfileMapper;
	
	@Override
	public int countByExample(CartoonfileExample example) {
		
		return this.cartoonfileMapper.countByExample(example);
	}

	@Override
	public int deleteByExample(CartoonfileExample example) {
		// TODO Auto-generated method stub
		return this.cartoonfileMapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return this.cartoonfileMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Cartoonfile record) {
		// TODO Auto-generated method stub
		return this.cartoonfileMapper.insert(record);
	}

	@Override
	public int insertSelective(Cartoonfile record) {
		// TODO Auto-generated method stub
		return this.cartoonfileMapper.insertSelective(record);
	}

	@Override
	public List<Cartoonfile> selectByExample(CartoonfileExample example) {
		// TODO Auto-generated method stub
		return this.cartoonfileMapper.selectByExample(example);
	}

	@Override
	public Cartoonfile selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return this.cartoonfileMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByExampleSelective(Cartoonfile record, CartoonfileExample example) {
		// TODO Auto-generated method stub
		return this.cartoonfileMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(Cartoonfile record, CartoonfileExample example) {
		// TODO Auto-generated method stub
		return this.cartoonfileMapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(Cartoonfile record) {
		// TODO Auto-generated method stub
		return this.cartoonfileMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Cartoonfile record) {
		// TODO Auto-generated method stub
		return this.cartoonfileMapper.updateByPrimaryKey(record);
	}

}
