package common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Template {
	public static SqlSession getSqlSession() {
		SqlSession session = null;
		try {
			SqlSessionFactoryBuilder ssfb = new SqlSessionFactoryBuilder();
			//         resources라는 폴더릴 가르킴
			InputStream stream = Resources.getResourceAsStream("/mybatis-config.xml");
			SqlSessionFactory ssf = ssfb.build(stream);
			session = ssf.openSession();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return session;
		//jdbc에서 getConnection과 비슷하게 만든것임
		
	}
}
