import axios from "axios";
import { useEffect, useState } from "react";

/**
 * ReimbursementComponent Component
 * @returns HTML
 */
export const ReimbursementComponent:React.FC = () => {
  // Reimbursement Setter
  const [reimbursements, setReimbursements] = useState([]);

  // Load reimbursements when component mounts
  useEffect(() => {
    getAllReimbursements();
  }, [])

  // Backend communication
  const getAllReimbursements = async () => {
    try {
      const response = await axios.get("http://localhost:150/reimbursements/all");
      setReimbursements(response.data)
    } catch (error) {
      console.log("Failed to get all reimbursements: ", error);
    }
  }

  // HTML
  return (
    <div>

    </div>
  )
}