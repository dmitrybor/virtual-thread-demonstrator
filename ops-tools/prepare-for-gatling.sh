#!/bin/bash

cd $VTD_PROJECT_DIR

# install jdk
source ./ops-tools/install-java.sh

# OS tuning
echo "soft    nofile  65535" >> /etc/security/limits.conf
echo "hard    nofile  65535" >> /etc/security/limits.conf

# more ports for testing
sysctl -w net.ipv4.ip_local_port_range="1025 65535"

# increase the maximum number of possible open file descriptors:
echo 300000 | tee /proc/sys/fs/nr_open
echo 300000 | tee /proc/sys/fs/file-max

# kernel and network tuning
echo "net.ipv4.tcp_max_syn_backlog = 40000" >> /etc/sysctl.conf
echo "net.core.somaxconn = 40000" >> /etc/sysctl.conf
echo "net.core.wmem_default = 8388608" >> /etc/sysctl.conf
echo "net.core.rmem_default = 8388608" >> /etc/sysctl.conf
echo "net.ipv4.tcp_sack = 1" >> /etc/sysctl.conf
echo "net.ipv4.tcp_window_scaling = 1" >> /etc/sysctl.conf
echo "net.ipv4.tcp_fin_timeout = 15" >> /etc/sysctl.conf
echo "net.ipv4.tcp_keepalive_intvl = 30" >> /etc/sysctl.conf
echo "net.ipv4.tcp_tw_reuse = 1" >> /etc/sysctl.conf
echo "net.ipv4.tcp_moderate_rcvbuf = 1" >> /etc/sysctl.conf
echo "net.core.rmem_max = 134217728" >> /etc/sysctl.conf
echo "net.core.wmem_max = 134217728" >> /etc/sysctl.conf
echo "net.ipv4.tcp_mem  = 134217728 134217728 134217728" >> /etc/sysctl.conf
echo "net.ipv4.tcp_rmem = 4096 277750 134217728" >> /etc/sysctl.conf
echo "net.ipv4.tcp_wmem = 4096 277750 134217728" >> /etc/sysctl.conf
echo "net.core.netdev_max_backlog = 300000" >> /etc/sysctl.conf

reboot
