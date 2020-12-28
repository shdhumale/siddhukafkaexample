package com.siddhu.kafka.example.siddhukafkaexample.simpleproducer;
import java.util.Date;
public class Address{
        private int houseId;
        private String societyName;
        private Date startDate;

        public Address(int houseId, String societyName, Date startDate){
                this.houseId = houseId;
                this.societyName = societyName;
                this.startDate = startDate;
        }

		/**
		 * @return the houseId
		 */
		public int getHouseId() {
			return houseId;
		}

		/**
		 * @param houseId the houseId to set
		 */
		public void setHouseId(int houseId) {
			this.houseId = houseId;
		}

		/**
		 * @return the societyName
		 */
		public String getSocietyName() {
			return societyName;
		}

		/**
		 * @param societyName the societyName to set
		 */
		public void setSocietyName(String societyName) {
			this.societyName = societyName;
		}

		/**
		 * @return the startDate
		 */
		public Date getStartDate() {
			return startDate;
		}

		/**
		 * @param startDate the startDate to set
		 */
		public void setStartDate(Date startDate) {
			this.startDate = startDate;
		}

		@Override
		public String toString() {
			return "Address [houseId=" + houseId + ", societyName=" + societyName + ", startDate=" + startDate + "]";
		}

       
}