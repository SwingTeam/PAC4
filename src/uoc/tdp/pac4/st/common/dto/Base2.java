package uoc.tdp.pac4.st.common.dto;


public abstract class Base2 {
		private String name;
		private int id;
		
		public Base2()
		{
			
		}
		public Base2(String name, int id)
		{
			this.name = name;
			this.id = id;
		}
		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}
		/**
		 * @param name the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}
		/**
		 * @return the id
		 */
		public int getId() {
			return id;
		}
		/**
		 * @param id the id to set
		 */
		public void setId(int id) {
			this.id = id;
		}
		
		
}
