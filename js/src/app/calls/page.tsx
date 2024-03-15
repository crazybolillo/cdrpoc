"use client";

import { useTable } from "@refinedev/antd";
import { Table } from "antd";

interface CallDetailRecord {
  id: number;
  from: string;
  to: string;
  startedAt: string;
  answeredAt: string;
  endedAt: string;
  duration: number;
  billed: number;
}

export default function CallList() {
  const { tableProps } = useTable<CallDetailRecord>({
    resource: "cdr",
    pagination: { current: 1, pageSize: 15 },
  });

  return (
    <Table {...tableProps} rowKey="id">
      <Table.Column dataIndex="id" title="ID" />
      <Table.Column dataIndex="from" title="From" />
      <Table.Column dataIndex="to" title="To" />
      <Table.Column dataIndex="startedAt" title="Started At" />
      <Table.Column dataIndex="answeredAt" title="Answered At" />
      <Table.Column dataIndex="endedAt" title="Ended At" />
      <Table.Column dataIndex="duration" title="Duration" />
      <Table.Column dataIndex="billed" title="Billed" />
    </Table>
  );
}
